# BookMarkTabLayout
A Tablayout with bookmark appearance as below.

![sample](https://github.com/bluesnow4425/BookmarkTabLayout/blob/main/static/style.png)

## Easy Implementation
Gradle

```gradle
dependencies {
    implementation 'com.github.bluesnow4425:BookmarkTabLayout:1.0.0'
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
        app:backgroundColor="@color/bg_selector" (optional, use color selector for different states)
        app:titleTextColor="@color/title_selector" (optional, use color selector for different states)
        app:hintTextColor="@color/hint_selector" (optional, use color selector for different states)
        />
```


### TabLayout
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
and add **setOnItemSelectedListener** to tablayout in kt files.
