package com.example.exam2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ModuleActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // تفعيل زر الرجوع للأعلى (Up Navigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Module Activity"

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // ربط الـ Adapter
        viewPager.adapter = ViewPagerAdapter(this)

        // ربط التبويبات مع ViewPager
        val tabTitles = arrayOf("First Tab", "Second Tab", "Third Tab")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    // ===== إنشاء القائمة المنبثقة (Popup Menu) =====
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    // ===== معالجة نقرات القائمة وزر الرجوع للأعلى =====
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Up Navigation -> العودة لـ MainActivity
                true
            }
            R.id.menu_light -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
            R.id.menu_night -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true
            }
            R.id.menu_system -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ===== سلوك الزر الخلفي (Back-stack) =====
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (viewPager.currentItem != 0) {
            // إذا كنا في التبويب 2 أو 3 -> الرجوع إلى التبويب الأول
            viewPager.currentItem = 0
        } else {
            // إذا كنا في التبويب الأول -> الخروج إلى MainActivity
            super.onBackPressed()
        }
    }
}