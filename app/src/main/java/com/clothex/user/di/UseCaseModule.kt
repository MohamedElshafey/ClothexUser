package com.clothex.user.di

import com.clothex.data.domain.usecases.database.DeleteLocationUseCase
import com.clothex.data.domain.usecases.database.GetSavedLocationUseCase
import com.clothex.data.domain.usecases.database.GetSavedLocationsUseCase
import com.clothex.data.domain.usecases.database.SaveLocationsUseCase
import com.clothex.data.domain.usecases.department.GetDepartmentsUseCase
import com.clothex.data.domain.usecases.filter.*
import com.clothex.data.domain.usecases.home.GetHomeUseCase
import com.clothex.data.domain.usecases.language.GetLanguageUseCase
import com.clothex.data.domain.usecases.language.SetLanguageUseCase
import com.clothex.data.domain.usecases.local.ClearFilterUseCase
import com.clothex.data.domain.usecases.local.GetIsFirstTimeOpenUseCase
import com.clothex.data.domain.usecases.local.LogoutUseCase
import com.clothex.data.domain.usecases.local.SetIsFirstTimeOpenUseCase
import com.clothex.data.domain.usecases.logging.LogEventUseCase
import com.clothex.data.domain.usecases.logging.LogScreenUseCase
import com.clothex.data.domain.usecases.my_item.CreateMyItemsUseCase
import com.clothex.data.domain.usecases.my_item.DeleteMyItemUseCase
import com.clothex.data.domain.usecases.my_item.DeleteMyItemsUseCase
import com.clothex.data.domain.usecases.my_item.GetMyItemsUseCase
import com.clothex.data.domain.usecases.notification.GetNotificationsUseCase
import com.clothex.data.domain.usecases.notification.ReadNotificationsUseCase
import com.clothex.data.domain.usecases.offer.GetOffersUseCase
import com.clothex.data.domain.usecases.offer.GetShopOffersUseCase
import com.clothex.data.domain.usecases.onboarding.GetVisitOnBoardingUseCase
import com.clothex.data.domain.usecases.onboarding.SetVisitOnBoardingUseCase
import com.clothex.data.domain.usecases.order.CreateMyOrdersUseCase
import com.clothex.data.domain.usecases.order.GetMyOrdersUseCase
import com.clothex.data.domain.usecases.order.GetOrderDetailsUseCase
import com.clothex.data.domain.usecases.product.GetProductDetailsUseCase
import com.clothex.data.domain.usecases.product.GetProductPagingUseCase
import com.clothex.data.domain.usecases.shop.GetShopDetailsUseCase
import com.clothex.data.domain.usecases.shop.GetShopPageUseCase
import com.clothex.data.domain.usecases.sign.*
import com.clothex.data.domain.usecases.sort.GetSortUseCase
import com.clothex.data.domain.usecases.sort.SetSortUseCase
import com.clothex.data.domain.usecases.token.GetTokenUseCase
import com.clothex.data.domain.usecases.token.SetTokenUseCase
import com.clothex.data.domain.usecases.user.GetUserUseCase
import com.clothex.data.domain.usecases.user.SetUserUseCase
import com.clothex.data.domain.usecases.voucher.AddVoucherUseCase
import com.clothex.data.domain.usecases.voucher.GetVouchersUseCase
import com.clothex.data.domain.usecases.voucher.RedeemVoucherUseCase
import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.shared_pref.LocalDataSourceImpl
import com.clothex.data.log.repository.LogEventRepository
import com.clothex.data.log.repository.LogScreenRepository
import com.clothex.data.remote.repository.GetHomeRepository
import com.clothex.data.remote.repository.GetProductDetailsRepository
import com.clothex.data.remote.repository.GetShopDetailsRepository
import com.clothex.data.remote.repository.department.GetDepartmentRepository
import com.clothex.data.remote.repository.my_item.CreateMyItemRepository
import com.clothex.data.remote.repository.my_item.DeleteMyItemRepository
import com.clothex.data.remote.repository.my_item.DeleteMyItemsRepository
import com.clothex.data.remote.repository.my_item.GetMyItemsRepository
import com.clothex.data.remote.repository.notification.GetNotificationRepository
import com.clothex.data.remote.repository.notification.ReadNotificationRepository
import com.clothex.data.remote.repository.offer.GetOffersRepository
import com.clothex.data.remote.repository.offer.GetShopOffersRepository
import com.clothex.data.remote.repository.order.CreateMyOrderRepository
import com.clothex.data.remote.repository.order.GetMyOrdersRepository
import com.clothex.data.remote.repository.order.GetOrderDetailsRepository
import com.clothex.data.remote.repository.search.GetProductPagingRepository
import com.clothex.data.remote.repository.search.GetShopPageRepository
import com.clothex.data.remote.repository.sign.LoginRepository
import com.clothex.data.remote.repository.sign.SignUpRepository
import com.clothex.data.remote.repository.sign.SignUpTemporaryRepository
import com.clothex.data.remote.repository.sign.UpdateFCMTokenRepository
import com.clothex.data.remote.repository.voucher.AddVoucherRepository
import com.clothex.data.remote.repository.voucher.GetVouchersRepository
import com.clothex.data.remote.repository.voucher.RedeemVoucherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val useCaseModule = module {
    single(named("get_home")) { provideGetHomeUseCase(get()) }
    single(named("get_product_paging")) { provideGetProductPagingUseCase(get()) }
    single(named("get_product_details")) { provideGetProductDetailsUseCase(get()) }
    single(named("clear_filter")) { provideClearSessionUseCase(get()) }
    single(named("logout")) { provideLogoutUseCase(get()) }

    single(named("get_shop_details")) { provideGetShopDetailsUseCase(get()) }

    single(named("get_sort")) { provideGetSortUseCase(get()) }
    single(named("set_sort")) { provideSetSortUseCase(get()) }

    single(named("get_size_filter")) { provideGetSizeFilterUseCase(get()) }
    single(named("set_size_filter")) { provideSetSizeFilterUseCase(get()) }

    single(named("get_color_filter")) { provideGetColorFilterUseCase(get()) }
    single(named("set_color_filter")) { provideSetColorFilterUseCase(get()) }

    single(named("get_price_start_filter")) { provideGetPriceStartFilterUseCase(get()) }
    single(named("set_price_start_filter")) { provideSetPriceStartFilterUseCase(get()) }

    single(named("get_price_end_filter")) { provideGetPriceEndFilterUseCase(get()) }
    single(named("set_price_end_filter")) { provideSetPriceEndFilterUseCase(get()) }

    single(named("get_my_items")) { provideGetMyItemsUseCase(get()) }
    single(named("create_my_item")) { provideCreateMyItemUseCase(get()) }
    single(named("delete_my_item")) { provideDeleteMyItemUseCase(get()) }
    single(named("delete_my_items")) { provideDeleteMyItemsUseCase(get()) }

    single(named("get_my_orders")) { provideGetMyOrdersUseCase(get()) }
    single(named("create_my_order")) { provideCreateMyOrderUseCase(get()) }
    single(named("get_order_details")) { provideGetOrderDetailsUseCase(get()) }

    single(named("get_shop_page")) { provideGetShopPageUseCase(get()) }

    single(named("get_token")) { provideGetTokenUseCase(get()) }
    single(named("set_token")) { provideSetTokenUseCase(get()) }

    single(named("get_user")) { provideGetUserUseCase(get()) }
    single(named("set_user")) { provideSetUserUseCase(get()) }

    single(named("get_language")) { provideGetLanguageUseCase(get()) }
    single(named("set_language")) { provideSetLanguageUseCase(get()) }

    single(named("signup_temporary")) { provideSignUpTemporaryUseCase(get()) }
    single(named("signup")) { provideSignUpUseCase(get()) }
    single(named("login")) {
        provideLoginUseCase(
            get(),
            get(named("set_token")),
            get(named("set_user"))
        )
    }

    single(named("get_visit_onboarding")) { provideGetVisitOnBoardingUseCase(get()) }
    single(named("set_visit_onboarding")) { provideSetVisitOnBoardingUseCase(get()) }

    single(named("get_login_temporary")) { provideGetIsLoginTemporaryUseCase(get()) }
    single(named("update_fcm_token")) { provideUpdateFCMTokenUseCase(get()) }

    single(named("get_is_first_time")) { provideGetIsFirstTimeOpenUseCase(get()) }
    single(named("set_is_first_time")) { provideSetIsFirstTimeOpenUseCase(get()) }

    single(named("save_location")) { provideSaveLocationUseCase(get()) }
    single(named("get_locations")) { provideGetSavedLocationsUseCase(get()) }
    single(named("get_location")) { provideGetSavedLocationUseCase(get()) }
    single(named("delete_location")) { provideDeleteLocationUseCase(get()) }

    single(named("get_vouchers")) { provideGetVouchersUseCase(get()) }
    single(named("add_voucher")) { provideAddVoucherUseCase(get()) }
    single(named("redeem_voucher")) { provideRedeemVoucherUseCase(get()) }

    single(named("get_offers")) { provideGetOffersUseCase(get()) }
    single(named("get_shop_offers")) { provideGetShopOffersUseCase(get()) }

    single(named("get_departments")) { provideGetDepartmentsUseCase(get()) }

    single(named("get_notifications")) { provideGetNotificationsUseCase(get()) }
    single(named("read_notifications")) { provideReadNotificationsUseCase(get()) }

    single(named("log_screen")) { provideLogScreenUseCase(get()) }
    single(named("log_event")) { provideLogEventUseCase(get()) }
}

fun provideGetHomeUseCase(repository: GetHomeRepository): GetHomeUseCase {
    return GetHomeUseCase(repository)
}

fun provideGetProductPagingUseCase(repository: GetProductPagingRepository): GetProductPagingUseCase {
    return GetProductPagingUseCase(repository)
}

fun provideGetProductDetailsUseCase(repository: GetProductDetailsRepository): GetProductDetailsUseCase {
    return GetProductDetailsUseCase(repository)
}

fun provideGetSortUseCase(dataSource: LocalDataSourceImpl): GetSortUseCase {
    return GetSortUseCase(dataSource)
}

fun provideSetSortUseCase(dataSource: LocalDataSourceImpl): SetSortUseCase {
    return SetSortUseCase(dataSource)
}

fun provideClearSessionUseCase(dataSource: LocalDataSourceImpl): ClearFilterUseCase {
    return ClearFilterUseCase(dataSource)
}

fun provideLogoutUseCase(dataSource: LocalDataSourceImpl): LogoutUseCase {
    return LogoutUseCase(dataSource)
}

fun provideGetSizeFilterUseCase(dataSource: LocalDataSourceImpl): GetSizeFilterUseCase {
    return GetSizeFilterUseCase(dataSource)
}

fun provideSetSizeFilterUseCase(dataSource: LocalDataSourceImpl): SetSizeFilterUseCase {
    return SetSizeFilterUseCase(dataSource)
}

fun provideGetColorFilterUseCase(dataSource: LocalDataSourceImpl): GetColorFilterUseCase {
    return GetColorFilterUseCase(dataSource)
}

fun provideSetColorFilterUseCase(dataSource: LocalDataSourceImpl): SetColorFilterUseCase {
    return SetColorFilterUseCase(dataSource)
}

fun provideGetPriceStartFilterUseCase(dataSource: LocalDataSourceImpl): GetPriceStartFilterUseCase {
    return GetPriceStartFilterUseCase(dataSource)
}

fun provideSetPriceStartFilterUseCase(dataSource: LocalDataSourceImpl): SetPriceStartFilterUseCase {
    return SetPriceStartFilterUseCase(dataSource)
}

fun provideGetPriceEndFilterUseCase(dataSource: LocalDataSourceImpl): GetPriceEndFilterUseCase {
    return GetPriceEndFilterUseCase(dataSource)
}

fun provideSetPriceEndFilterUseCase(dataSource: LocalDataSourceImpl): SetPriceEndFilterUseCase {
    return SetPriceEndFilterUseCase(dataSource)
}

fun provideGetShopDetailsUseCase(repository: GetShopDetailsRepository): GetShopDetailsUseCase {
    return GetShopDetailsUseCase(repository)
}

fun provideGetMyItemsUseCase(repository: GetMyItemsRepository): GetMyItemsUseCase {
    return GetMyItemsUseCase(repository)
}

fun provideCreateMyItemUseCase(repository: CreateMyItemRepository): CreateMyItemsUseCase {
    return CreateMyItemsUseCase(repository)
}

fun provideDeleteMyItemUseCase(repository: DeleteMyItemRepository): DeleteMyItemUseCase {
    return DeleteMyItemUseCase(repository)
}

fun provideDeleteMyItemsUseCase(repository: DeleteMyItemsRepository): DeleteMyItemsUseCase {
    return DeleteMyItemsUseCase(repository)
}

fun provideGetMyOrdersUseCase(repository: GetMyOrdersRepository): GetMyOrdersUseCase {
    return GetMyOrdersUseCase(repository)
}

fun provideCreateMyOrderUseCase(repository: CreateMyOrderRepository): CreateMyOrdersUseCase {
    return CreateMyOrdersUseCase(repository)
}

fun provideGetOrderDetailsUseCase(repository: GetOrderDetailsRepository): GetOrderDetailsUseCase {
    return GetOrderDetailsUseCase(repository)
}

fun provideGetShopPageUseCase(repository: GetShopPageRepository): GetShopPageUseCase {
    return GetShopPageUseCase(repository)
}

fun provideGetTokenUseCase(repository: LocalDataSourceImpl): GetTokenUseCase {
    return GetTokenUseCase(repository)
}

fun provideSetUserUseCase(repository: LocalDataSourceImpl): SetUserUseCase {
    return SetUserUseCase(repository)
}

fun provideGetUserUseCase(repository: LocalDataSourceImpl): GetUserUseCase {
    return GetUserUseCase(repository)
}

fun provideSetLanguageUseCase(repository: LocalDataSourceImpl): SetLanguageUseCase {
    return SetLanguageUseCase(repository)
}

fun provideGetLanguageUseCase(repository: LocalDataSourceImpl): GetLanguageUseCase {
    return GetLanguageUseCase(repository)
}

fun provideSetTokenUseCase(repository: LocalDataSourceImpl): SetTokenUseCase {
    return SetTokenUseCase(repository)
}

fun provideSignUpTemporaryUseCase(repository: SignUpTemporaryRepository): SignUpTemporaryUseCase {
    return SignUpTemporaryUseCase(repository)
}

fun provideSignUpUseCase(repository: SignUpRepository): SignUpUseCase {
    return SignUpUseCase(repository)
}

fun provideLoginUseCase(
    repository: LoginRepository,
    setTokenUseCase: SetTokenUseCase,
    setUserUseCase: SetUserUseCase
): LoginUseCase {
    return LoginUseCase(repository, setTokenUseCase, setUserUseCase)
}

fun provideGetVisitOnBoardingUseCase(repository: LocalDataSourceImpl): GetVisitOnBoardingUseCase {
    return GetVisitOnBoardingUseCase(repository)
}

fun provideSetVisitOnBoardingUseCase(repository: LocalDataSourceImpl): SetVisitOnBoardingUseCase {
    return SetVisitOnBoardingUseCase(repository)
}

fun provideGetIsLoginTemporaryUseCase(repository: LocalDataSourceImpl): GetIsLoginTemporaryUseCase {
    return GetIsLoginTemporaryUseCase(repository)
}

fun provideUpdateFCMTokenUseCase(repository: UpdateFCMTokenRepository): UpdateFCMTokenUseCase {
    return UpdateFCMTokenUseCase(repository)
}

fun provideGetIsFirstTimeOpenUseCase(repository: LocalDataSourceImpl): GetIsFirstTimeOpenUseCase {
    return GetIsFirstTimeOpenUseCase(repository)
}

fun provideSetIsFirstTimeOpenUseCase(repository: LocalDataSourceImpl): SetIsFirstTimeOpenUseCase {
    return SetIsFirstTimeOpenUseCase(repository)
}

fun provideSaveLocationUseCase(repository: SavedLocationDao): SaveLocationsUseCase {
    return SaveLocationsUseCase(repository)
}

fun provideGetSavedLocationsUseCase(repository: SavedLocationDao): GetSavedLocationsUseCase {
    return GetSavedLocationsUseCase(repository)
}

fun provideGetSavedLocationUseCase(repository: SavedLocationDao): GetSavedLocationUseCase {
    return GetSavedLocationUseCase(repository)
}

fun provideDeleteLocationUseCase(repository: SavedLocationDao): DeleteLocationUseCase {
    return DeleteLocationUseCase(repository)
}

fun provideGetVouchersUseCase(repository: GetVouchersRepository): GetVouchersUseCase {
    return GetVouchersUseCase(repository)
}

fun provideAddVoucherUseCase(repository: AddVoucherRepository): AddVoucherUseCase {
    return AddVoucherUseCase(repository)
}

fun provideRedeemVoucherUseCase(repository: RedeemVoucherRepository): RedeemVoucherUseCase {
    return RedeemVoucherUseCase(repository)
}

fun provideGetDepartmentsUseCase(repository: GetDepartmentRepository): GetDepartmentsUseCase {
    return GetDepartmentsUseCase(repository)
}

fun provideGetNotificationsUseCase(repository: GetNotificationRepository): GetNotificationsUseCase {
    return GetNotificationsUseCase(repository)
}

fun provideReadNotificationsUseCase(repository: ReadNotificationRepository): ReadNotificationsUseCase {
    return ReadNotificationsUseCase(repository)
}

fun provideGetOffersUseCase(repository: GetOffersRepository): GetOffersUseCase {
    return GetOffersUseCase(repository)
}

fun provideGetShopOffersUseCase(repository: GetShopOffersRepository): GetShopOffersUseCase {
    return GetShopOffersUseCase(repository)
}

fun provideLogScreenUseCase(repository: LogScreenRepository): LogScreenUseCase {
    return LogScreenUseCase(repository)
}

fun provideLogEventUseCase(repository: LogEventRepository): LogEventUseCase {
    return LogEventUseCase(repository)
}