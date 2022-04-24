package com.clothex.user.di

import com.clothex.user.home.book.BookViewModel
import com.clothex.user.home.categories.CategoriesViewModel
import com.clothex.user.home.categories.type.SelectTypeViewModel
import com.clothex.user.home.home.HomeViewModel
import com.clothex.user.home.location.SelectLocationViewModel
import com.clothex.user.home.login.LoginViewModel
import com.clothex.user.home.map.MapsViewModel
import com.clothex.user.home.notification.NotificationViewModel
import com.clothex.user.home.product_details.ProductDetailsViewModel
import com.clothex.user.home.register.RegisterViewModel
import com.clothex.user.home.search.SearchViewModel
import com.clothex.user.home.search.filter.FilterProductViewModel
import com.clothex.user.home.search.sort.SortProductViewModel
import com.clothex.user.home.shop.details.ShopDetailsViewModel
import com.clothex.user.my_items.items.MyItemsViewModel
import com.clothex.user.my_items.orders.ActiveOrdersViewModel
import com.clothex.user.my_items.orders.details.OrderDetailsViewModel
import com.clothex.user.offer.OfferViewModel
import com.clothex.user.onboarding.boarding.OnBoardingViewModel
import com.clothex.user.onboarding.splash.SplashViewModel
import com.clothex.user.profile.ProfileViewModel
import com.clothex.user.profile.edit.EditProfileViewModel
import com.clothex.user.profile.language.SelectLanguageViewModel
import com.clothex.user.profile.location.EditLocationViewModel
import com.clothex.user.voucher.VoucherViewModel
import com.clothex.user.voucher.add_text.CodeVoucherViewModel
import com.clothex.user.voucher.details.RedeemVoucherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val viewModelsModule = module {

    viewModel {
        SplashViewModel(
            getTokenUseCase = get(named("get_token")),
            setTokenUseCase = get(named("set_token")),
            signUpTemporaryUseCase = get(named("signup_temporary")),
            getVisitOnBoardingUseCase = get(named("get_visit_onboarding"))
        )
    }

    viewModel {
        HomeViewModel(
            homeUseCase = get(named("get_home")),
            updateFCMTokenUseCase = get(named("update_fcm_token")),
            getIsFirstTimeOpenUseCase = get(named("get_is_first_time")),
            getSavedLocationUseCase = get(named("get_location")),
            getDepartmentsUseCase = get(named("get_departments"))
        )
    }

    viewModel {
        ProductDetailsViewModel(
            getProductDetailsUseCase = get(named("get_product_details")),
            createMyItemsUseCase = get(named("create_my_item"))
        )
    }

    viewModel {
        SearchViewModel(
            shopPageUseCase = get(named("get_shop_page")),
            getSortUseCase = get(named("get_sort")),
            getSizeFilterUseCase = get(named("get_size_filter")),
            getColorFilterUseCase = get(named("get_color_filter")),
            getPriceStartFilterUseCase = get(named("get_price_start_filter")),
            getPriceEndFilterUseCase = get(named("get_price_end_filter")),
            getProductPagingUseCase = get(named("get_product_paging"))
        )
    }

    viewModel {
        SortProductViewModel(get(named("set_sort")), get(named("get_sort")))
    }

    viewModel {
        FilterProductViewModel(
            setSizeFilterUseCase = get(named("set_size_filter")),
            getSizeFilterUseCase = get(named("get_size_filter")),
            setColorFilterUseCase = get(named("set_color_filter")),
            getColorFilterUseCase = get(named("get_color_filter")),
            setPriceStartFilterUseCase = get(named("set_price_start_filter")),
            getPriceStartFilterUseCase = get(named("get_price_start_filter")),
            setPriceEndFilterUseCase = get(named("set_price_end_filter")),
            getPriceEndFilterUseCase = get(named("get_price_end_filter"))
        )
    }

    viewModel {
        ShopDetailsViewModel(get(named("get_shop_details")))
    }


    viewModel {
        MyItemsViewModel(
            getMyItemsUseCase = get(named("get_my_items")),
            deleteMyItemsUseCase = get(named("delete_my_items"))
        )
    }

    viewModel {
        BookViewModel(
            deleteMyItemUseCase = get(named("delete_my_item")),
            createMyOrdersUseCase = get(named("create_my_order")),
            getIsLoginTemporaryUseCase = get(named("get_login_temporary"))
        )
    }

    viewModel {
        ActiveOrdersViewModel(
            getMyOrdersUseCase = get(named("get_my_orders"))
        )
    }

    viewModel {
        OnBoardingViewModel(setVisitOnBoardingUseCase = get(named("set_visit_onboarding")))
    }

    viewModel {
        RegisterViewModel(
            signUpUseCase = get(named("signup")),
            getTokenUseCase = get(named("get_token")),
            setTokenUseCase = get(named("set_token")),
            signUpTemporaryUseCase = get(named("signup_temporary"))
        )
    }

    viewModel {
        LoginViewModel(
            loginUseCase = get(named("login")),
            setIsFirstTimeOpenUseCase = get(named("set_is_first_time")),
            getTokenUseCase = get(named("get_token")),
            setTokenUseCase = get(named("set_token")),
            signUpTemporaryUseCase = get(named("signup_temporary"))
        )
    }

    viewModel {
        SelectLocationViewModel(
            getSavedLocationsUseCase = get(named("get_locations")),
            saveLocationsUseCase = get(named("save_location"))
        )
    }

    viewModel {
        MapsViewModel(
            getSavedLocationsUseCase = get(named("get_locations")),
            saveLocationsUseCase = get(named("save_location"))
        )
    }

    viewModel {
        EditLocationViewModel(
            getSavedLocationsUseCase = get(named("get_locations")),
            deleteLocationUseCase = get(named("delete_location"))
        )
    }

    viewModel {
        VoucherViewModel(
            getVouchersUseCase = get(named("get_vouchers")),
            getIsLoginTemporaryUseCase = get(named("get_login_temporary"))
        )
    }

    viewModel {
        CodeVoucherViewModel(
            addVoucherUseCase = get(named("add_voucher"))
        )
    }

    viewModel {
        RedeemVoucherViewModel(redeemVoucherUseCase = get(named("redeem_voucher")))
    }

    viewModel {
        ProfileViewModel(
            getUserUseCase = get(named("get_user")),
            logoutUseCase = get(named("logout"))
        )
    }

    viewModel {
        EditProfileViewModel(getUserUseCase = get(named("get_user")))
    }

    viewModel {
        OrderDetailsViewModel(getOrderDetailsUseCase = get(named("get_order_details")))
    }

    viewModel {
        CategoriesViewModel(getDepartmentsUseCase = get(named("get_departments")))
    }

    viewModel {
        SelectTypeViewModel(
            productPagingUseCase = get(named("get_product_paging")),
            getSortUseCase = get(named("get_sort")),
            getSizeFilterUseCase = get(named("get_size_filter")),
            getColorFilterUseCase = get(named("get_color_filter")),
            getPriceStartFilterUseCase = get(named("get_price_start_filter")),
            getPriceEndFilterUseCase = get(named("get_price_end_filter"))
        )
    }

    viewModel {
        NotificationViewModel(
            getNotificationUseCase = get(named("get_notifications")),
            readNotificationsUseCase = get(named("read_notifications"))
        )
    }

    viewModel {
        SelectLanguageViewModel(
            getLanguageUseCase = get(named("get_language")),
            setLanguageUseCase = get(named("set_language"))
        )
    }


    viewModel {
        OfferViewModel(getOffersUseCase = get(named("get_offers")))
    }

}