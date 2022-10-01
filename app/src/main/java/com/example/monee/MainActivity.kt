package com.example.monee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.monee.calculator.CarLoanFragment
import com.example.monee.home.HomeFragment
import com.example.monee.home.InsertFragment
import com.example.monee.home.ListFragment
import com.example.monee.profile.ProfileFragment
import com.example.monee.report.DailyFragment
import com.example.monee.report.SummaryFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    //private lateinit var binding: ActivityMainBinding
    //private val nav by lazy { supportFragmentManager.findFragmentById(R.id.host)!!.findNavController()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        //setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        
        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu_id.setNavigationItemSelectedListener(this)

        setToolBarTitle("Home")
        //replaceFragment(ListFragment())

        //Setup action bar
        //setupActionBarWithNavController(nav)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //if(item.itemId == R.id.profile){
            //Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
        //}
        //return true

        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId){
            R.id.home -> {
                replaceFragment(HomeFragment())
                setToolBarTitle("Home")}
            R.id.summaryReport -> {
                replaceFragment(SummaryFragment())
                setToolBarTitle("Summary Report")
            }
            R.id.dailyReport -> {
                replaceFragment(DailyFragment())
                setToolBarTitle("Daily Report")
            }
            R.id.carloanCal -> {
                replaceFragment(CarLoanFragment())
                setToolBarTitle("Car Loan Calculator")
            }
            R.id.profile -> {
                replaceFragment(ProfileFragment())
                setToolBarTitle("Profile")
            }
            else -> {
            }
        }
        return true
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }

    fun setToolBarTitle(title:String){
        supportActionBar?.title = title
    }
}