package com.mohamedwessam.aklbety

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        //set category recycler view
        var categoryList = ArrayList<CategoryRecyclerView>()
        categoryList.add(
            CategoryRecyclerView(
                R.drawable.meals,
                "Meals"
            )
        )
        categoryList.add(
            CategoryRecyclerView(
                R.drawable.soup,
                "Soup"
            )
        )
        categoryList.add(
            CategoryRecyclerView(
                R.drawable.sandwishes,
                "Sandwiches"
            )
        )
        categoryList.add(
            CategoryRecyclerView(
                R.drawable.salad,
                "Salad"
            )
        )
        categoryList.add(
            CategoryRecyclerView(
                R.drawable.pizza,
                "Pizza"
            )
        )
        categoryList.add(
            CategoryRecyclerView(
                R.drawable.juice,
                "Juice"
            )
        )

        //set details recycler view
        var mealsDetailsList = ArrayList<DetailsRecyclerView>()
        var soupDetailsList = ArrayList<DetailsRecyclerView>()
        var sandwichesDetailsList = ArrayList<DetailsRecyclerView>()
        var saladDetailsList = ArrayList<DetailsRecyclerView>()
        var pizzaDetailsList = ArrayList<DetailsRecyclerView>()
        var juiceDetailsList = ArrayList<DetailsRecyclerView>()

        //
        mealsDetailsList.add(
            DetailsRecyclerView(
                R.drawable.checken,
                "Chicken",
                "80 LE"
            )
        )
        mealsDetailsList.add(
            DetailsRecyclerView(
                R.drawable.mahshi,
                "Mahshi",
                "42 LE"
            )
        )

        //
        soupDetailsList.add(
            DetailsRecyclerView(
                R.drawable.soup2,
                "Soup 1",
                "15 LE"
            )
        )
        soupDetailsList.add(
            DetailsRecyclerView(
                R.drawable.soup3,
                "Soup 2",
                "18 LE"
            )
        )

        //
        sandwichesDetailsList.add(
            DetailsRecyclerView(
                R.drawable.sandwich,
                "Sandwich 1",
                "30 LE"
            )
        )

        //
        saladDetailsList.add(
            DetailsRecyclerView(
                R.drawable.salad2,
                "Salad 1",
                "18 LE"
            )
        )

        //
        pizzaDetailsList.add(
            DetailsRecyclerView(
                R.drawable.pizza,
                "Pizza 1",
                "75 LE"
            )
        )

        //
        juiceDetailsList.add(
            DetailsRecyclerView(
                R.drawable.strawberry,
                "Strawberry",
                "20 LE"
            )
        )
        juiceDetailsList.add(
            DetailsRecyclerView(
                R.drawable.watermelon,
                "Watermelon",
                "15 LE"
            )
        )
        juiceDetailsList.add(
            DetailsRecyclerView(
                R.drawable.mango,
                "Mango",
                "20 LE"
            )
        )
        juiceDetailsList.add(
            DetailsRecyclerView(
                R.drawable.orange,
                "Orange",
                "10 LE"
            )
        )
        juiceDetailsList.add(
            DetailsRecyclerView(
                R.drawable.pomegranate,
                "Pomegranate",
                "25 LE"
            )
        )
        juiceDetailsList.add(
            DetailsRecyclerView(
                R.drawable.apple,
                "Apple",
                "15 LE"
            )
        )

        //set adapter for category recycler view
        category_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        var categoryAdapter = CategoryAdapter(categoryList)
        category_recycler_view.adapter = categoryAdapter

        //set adapter for details recycler view
        details_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        var mealsAdapter = DetailsAdapter(mealsDetailsList)
        var soupAdapter = DetailsAdapter(soupDetailsList)
        var sandwichesAdapter = DetailsAdapter(sandwichesDetailsList)
        var saladAdapter = DetailsAdapter(saladDetailsList)
        var pizzaAdapter = DetailsAdapter(pizzaDetailsList)
        var juiceAdapter = DetailsAdapter(juiceDetailsList)

        //set on click for category recycler view
        categoryAdapter.setOnItemClickListener(object : CategoryAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                when (pos) {
                    0 -> {
                        details_recycler_view.adapter = mealsAdapter
                    }
                    1 -> {
                        details_recycler_view.adapter = soupAdapter
                    }
                    2 -> {
                        details_recycler_view.adapter = sandwichesAdapter
                    }
                    3 -> {
                        details_recycler_view.adapter = saladAdapter
                    }
                    4 -> {
                        details_recycler_view.adapter = pizzaAdapter
                    }
                    5 -> {
                        details_recycler_view.adapter = juiceAdapter
                    }
                }
            }
        })


        //set on click for details recycler view
        mealsAdapter.setOnItemClickListener(object : DetailsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainActivity, DetailsPreview::class.java)
                intent.putExtra("MEALS_KEY", pos)
                startActivity(intent)
            }
        })

        soupAdapter.setOnItemClickListener(object : DetailsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainActivity, DetailsPreview::class.java)
                intent.putExtra("SOUP_KEY", pos)
                startActivity(intent)
            }
        })

        saladAdapter.setOnItemClickListener(object : DetailsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainActivity, DetailsPreview::class.java)
                intent.putExtra("SALAD_KEY", pos)
                startActivity(intent)
            }
        })

        sandwichesAdapter.setOnItemClickListener(object : DetailsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainActivity, DetailsPreview::class.java)
                intent.putExtra("SANDWICH_KEY", pos)
                startActivity(intent)
            }
        })

        pizzaAdapter.setOnItemClickListener(object : DetailsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainActivity, DetailsPreview::class.java)
                intent.putExtra("PIZZA_KEY", pos)
                startActivity(intent)
            }
        })

        juiceAdapter.setOnItemClickListener(object : DetailsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainActivity, DetailsPreview::class.java)
                intent.putExtra("JUICE_KEY", pos)
                startActivity(intent)
            }
        })


        //setting navigation view
        mDrawerLayout = findViewById(R.id.main_drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.main_nav_view)
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
        if (mAuth.currentUser == null) {
            startActivity(Intent(this, Slogan::class.java))
        }
    }

}

data class CategoryRecyclerView(var mCategoryImg: Int, var mCategoryName: String)

data class DetailsRecyclerView(var mDetailsImg: Int, var mDetailsName: String, var mDetailsPrice: String)