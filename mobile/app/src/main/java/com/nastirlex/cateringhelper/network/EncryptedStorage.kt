package com.nastirlex.cateringhelper.network

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.nastirlex.cateringhelper.Constants.CUISINES_KEY
import com.nastirlex.cateringhelper.Constants.INT_DEFAULT_VALUE
import com.nastirlex.cateringhelper.Constants.IS_VEGETARIAN_DEFAULT_VALUE
import com.nastirlex.cateringhelper.Constants.IS_VEGETARIAN_KEY
import com.nastirlex.cateringhelper.Constants.MAX_PRICE_KEY
import com.nastirlex.cateringhelper.Constants.MIN_PRICE_KEY
import com.nastirlex.cateringhelper.Constants.RATING_KEY
import com.nastirlex.cateringhelper.Constants.STRING_DEFAULT_VALUE
import com.nastirlex.cateringhelper.Constants.UNWANTED_INGREDIENTS_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EncryptedStorage @Inject constructor(@ApplicationContext context: Context) {
    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "preferences",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveIsVegetarian(isVegetarian: Boolean) {
        with(editor) {
            putBoolean(IS_VEGETARIAN_KEY, isVegetarian)
            apply()
        }
    }

    fun getIsVegetarian(): Boolean =
        sharedPreferences.getBoolean(IS_VEGETARIAN_KEY, IS_VEGETARIAN_DEFAULT_VALUE)

    fun saveCuisines(cuisines: String) {
        with(editor) {
            putString(CUISINES_KEY, cuisines)
            apply()
        }
    }

    fun getCuisines(): String =
        sharedPreferences.getString(CUISINES_KEY, STRING_DEFAULT_VALUE).toString()

    fun saveUnwantedIngredients(ingredients: String) {
        with(editor) {
            putString(UNWANTED_INGREDIENTS_KEY, ingredients)
            apply()
        }
    }

    fun getUnwantedIngredients(): String =
        sharedPreferences.getString(UNWANTED_INGREDIENTS_KEY, STRING_DEFAULT_VALUE).toString()

    fun saveRating(rating: Int) {
        with(editor) {
            putInt(RATING_KEY, rating)
            apply()
        }
    }

    fun getRating(): Int =
        sharedPreferences.getInt(RATING_KEY, INT_DEFAULT_VALUE)

    fun saveMinPrice(minPrice: Int) {
        with(editor) {
            putInt(MIN_PRICE_KEY, minPrice)
            apply()
        }
    }

    fun getMinPrice(): Int =
        sharedPreferences.getInt(MIN_PRICE_KEY, INT_DEFAULT_VALUE)

    fun saveMaxPrice(maxPrice: Int) {
        with(editor) {
            putInt(MAX_PRICE_KEY, maxPrice)
            apply()
        }
    }

    fun getMaxPrice(): Int =
        sharedPreferences.getInt(MAX_PRICE_KEY, INT_DEFAULT_VALUE)
}