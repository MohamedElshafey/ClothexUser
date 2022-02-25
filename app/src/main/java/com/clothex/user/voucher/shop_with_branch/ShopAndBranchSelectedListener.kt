package com.clothex.user.voucher.shop_with_branch

import com.clothex.data.domain.model.ShopAndBranch

interface ShopAndBranchSelectedListener {
    fun onItemSelected(shopAndBranch: ShopAndBranch)
}