# BookMarkTabLayout
A Tablayout with bookmark appearance as below.

![sample](https://github.com/bluesnow4425/BookmarkTabLayout/blob/main/static/style.png)

## Easy Implementation
Use Gradle

```gradle
dependencies {
    implementation 'com.github.bluesnow4425:BookmarkTabLayout:1.0.1'
}
```
Therefore, you can add either signle tab or tablayout in your layout.xml

### Single Tab

```xml
<com.bluesnow4425.bookmarkTablayout.BookMarkTab
        ...
        app:title="Title 1" 
        app:icon="@drawable/icon" (optional)
        app:hint="Hint 1"    (optional)
        app:style="sharp/rounded" (optional, default:rounded)
        app:titleTextSize="12sp" (optional)
        app:hintTextSize="10sp" (optional)
        app:backgroundColor="@color/selector1" (optional, use color selector for different states)
        app:titleTextColor="@color/selector2" (optional, use color selector for different states)
        />
```


### TabLayout/Multiple Tabs As A Group
```xml
<com.bluesnow4425.bookmarkTablayout.BookMarkTabLayout
      ...
      android:id= "@+id/tabs"
      app:tabTitleTextSize="10sp" 
      app:style="sharp" (optional, default to rounded, set the attribute on tablayout instead of single tab to unify)
      app:tabBackgroundColor="@color/tab_bg_color" (optional, use color selector for different states)
      >

  <com.bluesnow4425.bookmarkTablayout.BookMarkTab
        ...
        android:id="@+id/tab1"
        app:title="Title 1" />

  <com.bluesnow4425.bookmarkTablayout.BookMarkTab
        ...
        android:id="@+id/tab2"
        app:title="Title 2" />

</com.bluesnow4425.bookmarkTablayout.BookMarkTabLayout>
```
and add *setOnItemSelectedListener()* to tablayout in kt files to get notified or *getSelectedIndex()* to get selected item.

**Limitation: Only inflate in layout.xml is possible at the moment, add tab programatically haven't implemented. You're welcome to download the project and modify by yourself. ^^**
