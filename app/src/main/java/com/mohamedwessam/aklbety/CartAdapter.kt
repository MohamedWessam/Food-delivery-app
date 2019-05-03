package com.mohamedwessam.aklbety

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.cart_list.view.*

class CartAdapter (context: Context, cartList: ArrayList<MyCart>): ArrayAdapter<MyCart>(context, 0, cartList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(context).inflate(R.layout.cart_list, parent, false)

        val myCart: MyCart? = getItem(position)
        view.txt_cart_meal.text = myCart?.mCMealName
        view.txt_cart_price.text = myCart?.mCMealPrice
        view.txt_num_of_meals_cart.text = myCart?.mCMealNumber
        view.txt_total_price_cart.text = myCart?.mCMealTotalPrice
        return view
    }

}
