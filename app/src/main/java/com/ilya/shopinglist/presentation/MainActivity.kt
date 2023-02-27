package com.ilya.shopinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ilya.shopinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }

        binding.addItemButton.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                val fragment = ShopItemFragment.newInstanceAddItem()
                lunchFragment(fragment)
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.shopItemContainer == null
    }

    private fun lunchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(binding.shopItemContainer!!.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        shopListAdapter = ShopListAdapter()
        with(binding.shopListRv) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ENABLE_STATE, ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.DISABLE_STATE, ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setupShopItemLongClickListener()
        setupShopItemClickListener()
        setupShopItemDeleteSwipeCallback(binding.shopListRv)
    }

    private fun setupShopItemDeleteSwipeCallback(recyclerView: RecyclerView) {
        val touchCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopItem = shopListAdapter.currentList[viewHolder.adapterPosition]
                mainViewModel.removeShopItem(shopItem)
            }
        }

        ItemTouchHelper(touchCallback).attachToRecyclerView(recyclerView)
    }

    private fun setupShopItemClickListener() {
        shopListAdapter.onShopItemClickListener = { item ->
            if (isOnePaneMode()) {
                startActivity(ShopItemActivity.newIntentEditItem(this, item.id))
            } else {
                lunchFragment(ShopItemFragment.newInstanceEditItem(item.id))
            }
        }
    }

    private fun setupShopItemLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = { shopItem ->
            mainViewModel.changeEnabledItem(shopItem)
        }
    }

    override fun onEditingFinished() {
        supportFragmentManager.popBackStack()
    }
}