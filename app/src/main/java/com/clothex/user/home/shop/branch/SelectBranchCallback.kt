package com.clothex.user.home.shop.branch

import com.clothex.data.domain.model.product.Branch

interface SelectBranchCallback {
    fun onBranchSelected(branch: Branch)
}