package com.mohamedwessam.aklbety

class MyCart {
    var id: String? = null
    var mCMealName: String? = null
    var mCMealPrice: String? = null
    var mCMealNumber: String? = null
    var mCMealTotalPrice: String? = null

    constructor(){
     }

    constructor(id: String, mCMealName: String, mCMealPrice: String, mCMealNumber: String, mCMealTotalPrice: String){
        this.id = id
        this.mCMealName = mCMealName
        this.mCMealPrice = mCMealPrice
        this.mCMealNumber = mCMealNumber
        this.mCMealTotalPrice = mCMealTotalPrice
    }

}