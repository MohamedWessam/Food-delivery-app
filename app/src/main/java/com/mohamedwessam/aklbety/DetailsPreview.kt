package com.mohamedwessam.aklbety

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.preview_app_bar.*
import kotlinx.android.synthetic.main.preview_content.*
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class DetailsPreview : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var mDrawerLayout: DrawerLayout
    var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_preview)

        mAuth = FirebaseAuth.getInstance()

        //receiving position and set preview details
        var pos1 = intent.getIntExtra("MEALS_KEY", 1)
        var pos2 = intent.getIntExtra("SOUP_KEY", 2)
        var pos3 = intent.getIntExtra("SANDWICH_KEY", 3)
        var pos4 = intent.getIntExtra("SALAD_KEY", 4)
        var pos5 = intent.getIntExtra("PIZZA_KEY", 5)
        var pos6 = intent.getIntExtra("JUICE_KEY", 6)

        when (pos1) {
            0 -> {
                details_preview_txt_name.text = "Chicken"
                details_preview_txt_details.text = "details for meal 1"
                details_preview_txt_price.text = "80"
                details_preview_img.setImageResource(R.drawable.checken)
            }
            1 -> {
                details_preview_txt_name.text = "Mahshi"
                details_preview_txt_details.text = "details for meal 2"
                details_preview_txt_price.text = "42"
                details_preview_img.setImageResource(R.drawable.mahshi)
            }
        }

        //
        when (pos2) {
            0 -> {
                details_preview_txt_name.text = "Soup 1"
                details_preview_txt_details.text = "details for soup 1"
                details_preview_txt_price.text = "15"
                details_preview_img.setImageResource(R.drawable.soup2)
            }
            1 -> {
                details_preview_txt_name.text = "Soup 2"
                details_preview_txt_details.text = "details for soup 2"
                details_preview_txt_price.text = "18"
                details_preview_img.setImageResource(R.drawable.soup3)
            }
        }

        //
        when (pos3) {
            0 -> {
                details_preview_txt_name.text = "Sandwich 1"
                details_preview_txt_details.text = "details for Sandwich 1"
                details_preview_txt_price.text = "30"
                details_preview_img.setImageResource(R.drawable.sandwich)
            }
        }

        //
        when (pos4) {
            0 -> {
                details_preview_txt_name.text = "Salad 1"
                details_preview_txt_details.text = "details for salad 1"
                details_preview_txt_price.text = "18"
                details_preview_img.setImageResource(R.drawable.salad2)
            }
        }

        //
        when (pos5) {
            0 -> {
                details_preview_txt_name.text = "Pizza 1"
                details_preview_txt_details.text = "details for pizza 1"
                details_preview_txt_price.text = "75"
                details_preview_img.setImageResource(R.drawable.pizza)
            }
        }

        //
        when (pos6) {
            0 -> {
                details_preview_txt_name.text = "Strawberry"
                details_preview_txt_details.text = "details for strawberry"
                details_preview_txt_price.text = "20"
                details_preview_img.setImageResource(R.drawable.strawberry)
            }
            1 -> {
                details_preview_txt_name.text = "Watermelon"
                details_preview_txt_details.text = "details for watermelon"
                details_preview_txt_price.text = "15"
                details_preview_img.setImageResource(R.drawable.watermelon)
            }
            2 -> {
                details_preview_txt_name.text = "Mango"
                details_preview_txt_details.text = "details for Mango"
                details_preview_txt_price.text = "20"
                details_preview_img.setImageResource(R.drawable.mango)
            }
            3 -> {
                details_preview_txt_name.text = "Orange"
                details_preview_txt_details.text = "details for orange"
                details_preview_txt_price.text = "10"
                details_preview_img.setImageResource(R.drawable.orange)
            }
            4 -> {
                details_preview_txt_name.text = "Pomegranate"
                details_preview_txt_details.text = "details for pomegranate"
                details_preview_txt_price.text = "25"
                details_preview_img.setImageResource(R.drawable.pomegranate)
            }
            5 -> {
                details_preview_txt_name.text = "Apple"
                details_preview_txt_details.text = "details for apple"
                details_preview_txt_price.text = "15"
                details_preview_img.setImageResource(R.drawable.apple)
            }
        }


        //add meal
        var currentNum = 1
        btn_add_meal.setOnClickListener {
            currentNum += 1
            txt_num_of_meals.text = currentNum.toString()
            var n2 = details_preview_txt_price.text.toString().toInt()
            var total = currentNum * n2
            txt_total_price.text = "$total LE"
        }

        //remove meal
        btn_remove_meal.setOnClickListener {
            if (currentNum <= 1) {
                currentNum = 1
            } else {
                currentNum -= 1
            }
            txt_num_of_meals.text = currentNum.toString()
            var n2 = details_preview_txt_price.text.toString().toInt()
            var total = currentNum * n2
            txt_total_price.text = "$total LE"
        }

        //send meal to cart
        val database = FirebaseDatabase.getInstance()
        mRef = database.getReference("cartorder")

        btn_add_meal_to_market.setOnClickListener {
            var snackbar = Snackbar.make(details_preview_layout, "Your order has been added to your cart", Snackbar.LENGTH_LONG)
            snackbar.show()

            // Write a message to the database
            var id = mRef!!.push().key

            var mealName = details_preview_txt_name.text.toString()
            var mealPrice = details_preview_txt_price.text.toString()
            var mealNumber = txt_num_of_meals.text.toString()
            var mealTotalPrice = txt_total_price.text.toString()

            var myCart = MyCart(id!!, mealName, mealPrice, mealNumber, mealTotalPrice)

            mRef!!.child(mealName).setValue(myCart)

        }


        //setting navigation view
        val toolbar: Toolbar = findViewById(R.id.preview_toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        mDrawerLayout = findViewById(R.id.preview_drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.details_nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here
            when (menuItem.itemId) {
                R.id.mean_menu_item_cart -> {
                    var i = Intent(this, Cart::class.java)
                    startActivity(i)
                }
                R.id.mean_menu_item_logout -> {
                    mAuth.signOut()
                    startActivity(Intent(this, SignUp::class.java))
                }
                R.id.mean_menu_item_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
            true
        }

    }
}