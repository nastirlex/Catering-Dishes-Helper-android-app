package com.nastirlex.cateringhelper.data.encryptedSharedPrefs;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\fJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fJ\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u000eJ\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0011J\u000e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0011J\u000e\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0011J\u000e\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/nastirlex/cateringhelper/data/encryptedSharedPrefs/EncryptedStorage;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "editor", "Landroid/content/SharedPreferences$Editor;", "masterKeyAlias", "Landroidx/security/crypto/MasterKey;", "sharedPreferences", "Landroid/content/SharedPreferences;", "getCuisines", "", "getIsVegetarian", "", "getLabel", "getMaxPrice", "", "getMinPrice", "getRating", "getUnwantedIngredients", "saveCuisines", "", "cuisines", "saveIsVegetarian", "isVegetarian", "saveLabel", "label", "saveMaxPrice", "maxPrice", "saveMinPrice", "minPrice", "saveRating", "rating", "saveUnwantedIngredients", "ingredients", "app_debug"})
public final class EncryptedStorage {
    @org.jetbrains.annotations.NotNull
    private final androidx.security.crypto.MasterKey masterKeyAlias = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences.Editor editor = null;
    
    @javax.inject.Inject
    public EncryptedStorage(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final void saveIsVegetarian(boolean isVegetarian) {
    }
    
    public final boolean getIsVegetarian() {
        return false;
    }
    
    public final void saveCuisines(@org.jetbrains.annotations.NotNull
    java.lang.String cuisines) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCuisines() {
        return null;
    }
    
    public final void saveUnwantedIngredients(@org.jetbrains.annotations.NotNull
    java.lang.String ingredients) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUnwantedIngredients() {
        return null;
    }
    
    public final void saveRating(int rating) {
    }
    
    public final int getRating() {
        return 0;
    }
    
    public final void saveMinPrice(int minPrice) {
    }
    
    public final int getMinPrice() {
        return 0;
    }
    
    public final void saveMaxPrice(int maxPrice) {
    }
    
    public final int getMaxPrice() {
        return 0;
    }
    
    public final void saveLabel(@org.jetbrains.annotations.NotNull
    java.lang.String label) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLabel() {
        return null;
    }
}