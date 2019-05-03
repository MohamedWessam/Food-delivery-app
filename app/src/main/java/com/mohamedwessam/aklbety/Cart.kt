package com.mohamedwessam.aklbety

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.widget.AdapterView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_dialog.view.*

class Cart : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var mDrawerLayout: DrawerLayout

    var mRef: DatabaseReference? = null
    var mCartList: ArrayList<MyCart>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        mAuth = FirebaseAuth.getInstance()

        val database = FirebaseDatabase.getInstance()
        mRef = database.getReference("cartorder")

        //set category recycler view
        mCartList = ArrayList()

        cart_list_view.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { parent, view, position, id ->

                val alertBuilder = AlertDialog.Builder(this)
                var view = layoutInflater.inflate(R.layout.cart_dialog, null)
                var alertDialog = alertBuilder.create()
                alertDialog.setView(view)
                alertDialog.show()

                var myCart = mCartList?.get(position)!!
                view.txt_num_of_meals_dialog.text = myCart.mCMealNumber

                //add meal from alert dialog
                var currentNum = view.txt_num_of_meals_dialog.text.toString().toInt()
                view.btn_add_meal_dialog.setOnClickListener {
                    currentNum += 1
                    view.txt_num_of_meals_dialog.text = currentNum.toString()
                }

                //remove meal from alert dialog
                view.btn_remove_meal_dialog.setOnClickListener {
                    if (currentNum <= 1) {
                        currentNum = 1
                    } else {
                        currentNum -= 1
                    }
                    view.txt_num_of_meals_dialog.text = currentNum.toString()
                }

                //update meal information
                view.btn_update_dialog.setOnClickListener {
                    var childRef = mRef?.child(myCart.mCMealName!!)

                    var newNum = view.txt_num_of_meals_dialog.text.toString()
                    var n = myCart.mCMealPrice
                    var ni = n?.toInt()
                    var newNumInt = newNum.toInt()
                    var newTotalPriceInt = ni!! * newNumInt
                    var newTotalPrice = newTotalPriceInt.toString()

                    var afterUpdate = MyCart(myCart.id!!, myCart.mCMealName!!, myCart.mCMealPrice!!, newNum, newTotalPrice)
                    childRef?.setValue(afterUpdate)
                    alertDialog.dismiss()
                }

                //cancel alert dialog
                view.btn_cancel_dialog.setOnClickListener {
                    alertDialog.dismiss()
                }

                //delete meal from cart
                view.btn_delete_dialog.setOnClickListener {
                    mRef?.child(myCart.mCMealName!!)?.removeValue()
                    alertDialog.dismiss()
                }

                false
            }


        btn_confirm_meal.setOnClickListener {
            mRef = database.getReference("Confirmed")

        }


        mDrawerLayout = findViewById(R.id.cart_drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.cart_nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here
            var i = Intent(this, Cart::class.java)
            when (menuItem.itemId) {
                R.id.mean_menu_item_cart -> {
                    startActivity(i)
                }
                R.id.mean_menu_item_logout -> {
                    mAuth.signOut()
                    startActivity(Intent(this, SignUp::class.java))
                }
            }
            true
        }


    }

    override fun onStart() {
        super.onStart()
        mRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                mCartList?.clear()
                for (n in p0.children) {
                    var cart = n.getValue(MyCart::class.java)
                    mCartList?.add(0, cart!!)
                }

                val cartAdapter = CartAdapter(applicationContext, mCartList!!)
                cart_list_view.adapter = cartAdapter
            }
        })
    }

}