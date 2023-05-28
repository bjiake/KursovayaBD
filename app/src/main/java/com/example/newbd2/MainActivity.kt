package com.example.newbd2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.newbd2.Collections.book.BookFragment
import com.example.newbd2.Collections.goToFragment
import com.example.newbd2.retrofit.RetrofitAPI
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import java.util.ArrayList
import java.util.Arrays

class MainActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {
    private var mMenuAdapter: MenuAdapter? = null
    private var mViewHolder: ViewHolder? = null
    private var mTitles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTitles = ArrayList(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))


        // Initialize the views
        mViewHolder = ViewHolder()

        // Handle toolbar actions
        handleToolbar()

        // Handle menu actions
        handleMenu()

        // Handle drawer actions
        handleDrawer()

        // Show main fragment in container
        goToFragment(BookFragment(), false)
        mMenuAdapter!!.setViewSelected(0, true)
        title = mTitles[0]
    }

    private fun handleToolbar() {
        setSupportActionBar(mViewHolder!!.mToolbar)
    }

    private fun handleDrawer() {
        val DrawerToggle = DuoDrawerToggle(
            this,
            mViewHolder!!.mDrawerLayout,
            mViewHolder!!.mToolbar,
            nl.psdcompany.psd.duonavigationdrawer.R.string.navigation_drawer_open,
//            R.string.navigation_drawer_open,
            nl.psdcompany.psd.duonavigationdrawer.R.string.navigation_drawer_close
//            R.string.navigation_drawer_close
        )
        mViewHolder!!.mDrawerLayout.setDrawerListener(DrawerToggle)
        DrawerToggle.syncState()
    }

    private fun handleMenu() {
        mMenuAdapter = MenuAdapter(mTitles)
        mViewHolder!!.mDuoMenuView.setOnMenuClickListener(this)
        mViewHolder!!.mDuoMenuView.adapter = mMenuAdapter
    }

    override fun onFooterClicked() {
        Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show()
    }

    override fun onHeaderClicked() {
        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show()
    }


    override fun onOptionClicked(position: Int, objectClicked: Any) {
        // Set the toolbar title
        title = mTitles[position]

        // Set the right options selected
        // Тут вставлять нужный фрагмент, в зависимости от позиции
        mMenuAdapter!!.setViewSelected(position, true)
        when (position) {
            else -> goToFragment(BookFragment(), false)
        }

        // Close the drawer
        mViewHolder!!.mDrawerLayout.closeDrawer()
    }

    private inner class ViewHolder internal constructor() {
        val mDrawerLayout: DuoDrawerLayout
        val mDuoMenuView: DuoMenuView
        val mToolbar: Toolbar

        init {
            mDrawerLayout = findViewById<View>(R.id.drawer) as DuoDrawerLayout
            mDuoMenuView = mDrawerLayout.menuView as DuoMenuView
            mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        }
    }
}
