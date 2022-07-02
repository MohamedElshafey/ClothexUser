package com.clothex.data.remote.repository.search

import androidx.paging.PagingState
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.repository.search.IGetProductPagingRepository
import com.clothex.data.remote.api.ProductApiService

class GetProductPagingRepository(private val apiService: ProductApiService) :
    IGetProductPagingRepository() {
    var page: Int = 0
    var sort: String? = null
    var priceStart: Int? = null
    var priceEnd: Int? = null
    var size: String? = null
    var shopId: String? = null
    var color: String? = null
    var type: String? = null
    var department: String? = null
    var search: String? = null

    override suspend fun getProducts(
        page: Int,
        sort: String?,
        priceStart: Int?,
        priceEnd: Int?,
        size: String?,
        shopId: String?,
        color: String?,
        type: String?,
        department: String?,
        search: String?
    ) {
        this.page = page
        this.sort = sort
        this.priceStart = priceStart
        this.priceEnd = priceEnd
        this.size = size
        this.shopId = shopId
        this.color = color
        this.type = type
        this.department = department
        this.search = search
    }

    override fun getRefreshKey(state: PagingState<Int, HomeProduct>): Int? {
        val anchorPosition: Int = state.anchorPosition ?: return null

        var (_, prevKey, nextKey) = state.closestPageToPosition(anchorPosition)
            ?: return null

        if (prevKey != null) {
            return --prevKey
        }

        return if (nextKey != null) {
            return ++nextKey
        } else null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeProduct> {
        val nextPageNumber = params.key ?: 1
        val response = apiService.getProductPaging(
            page = nextPageNumber,
            sort = sort,
            priceStart = priceStart,
            priceEnd = priceEnd,
            shopId = shopId,
            size = size,
            color = color,
            type = type,
            department = department,
            search = search
        )
        return try {
            LoadResult.Page(
                data = response.docs,
                prevKey = null,
                nextKey = response.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}