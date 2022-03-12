package com.clothex.data.remote.repository.search

import androidx.paging.*
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.repository.search.IGetProductPagingRepository
import com.clothex.data.remote.api.ProductApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetProductPagingRepository(private val apiService: ProductApiService) :
    IGetProductPagingRepository {

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
        search: String?,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<HomeProduct>> {
        val pagingSource = object : PagingSource<Int, HomeProduct>() {
            override fun getRefreshKey(state: PagingState<Int, HomeProduct>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
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
        return Pager(PagingConfig(pageSize = 10)) { pagingSource }.flow.cachedIn(coroutineScope)
    }

}