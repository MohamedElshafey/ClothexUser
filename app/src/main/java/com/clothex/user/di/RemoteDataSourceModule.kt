package com.clothex.user.di

import com.clothex.data.remote.repository.GetHomeRepository
import com.clothex.data.remote.repository.GetProductDetailsRepository
import com.clothex.data.remote.repository.GetShopDetailsRepository
import com.clothex.data.remote.repository.department.GetDepartmentRepository
import com.clothex.data.remote.repository.my_item.CreateMyItemRepository
import com.clothex.data.remote.repository.my_item.DeleteMyItemRepository
import com.clothex.data.remote.repository.my_item.DeleteMyItemsRepository
import com.clothex.data.remote.repository.my_item.GetMyItemsRepository
import com.clothex.data.remote.repository.order.CreateMyOrderRepository
import com.clothex.data.remote.repository.order.GetMyOrdersRepository
import com.clothex.data.remote.repository.order.GetOrderDetailsRepository
import com.clothex.data.remote.repository.search.GetProductPageRepository
import com.clothex.data.remote.repository.search.GetShopPageRepository
import com.clothex.data.remote.repository.sign.LoginRepository
import com.clothex.data.remote.repository.sign.SignUpRepository
import com.clothex.data.remote.repository.sign.SignUpTemporaryRepository
import com.clothex.data.remote.repository.sign.UpdateFCMTokenRepository
import com.clothex.data.remote.repository.voucher.AddVoucherRepository
import com.clothex.data.remote.repository.voucher.GetVouchersRepository
import com.clothex.data.remote.repository.voucher.RedeemVoucherRepository
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val remoteDataSourceModule = module {
    single { GetHomeRepository(apiService = get()) }
    single { GetProductDetailsRepository(apiService = get()) }
    single { GetProductPageRepository(apiService = get()) }
    single { GetShopDetailsRepository(apiService = get()) }
    single { GetMyItemsRepository(apiService = get()) }
    single { CreateMyItemRepository(apiService = get()) }
    single { DeleteMyItemRepository(apiService = get()) }
    single { DeleteMyItemsRepository(apiService = get()) }
    single { CreateMyOrderRepository(apiService = get()) }
    single { GetMyOrdersRepository(apiService = get()) }
    single { GetShopPageRepository(apiService = get()) }
    single { SignUpTemporaryRepository(apiService = get()) }
    single { SignUpRepository(apiService = get()) }
    single { LoginRepository(apiService = get()) }
    single { UpdateFCMTokenRepository(apiService = get()) }
    single { GetVouchersRepository(apiService = get()) }
    single { AddVoucherRepository(apiService = get()) }
    single { RedeemVoucherRepository(apiService = get()) }
    single { GetOrderDetailsRepository(apiService = get()) }
    single { GetDepartmentRepository(apiService = get()) }
}