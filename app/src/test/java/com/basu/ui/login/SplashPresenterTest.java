package com.basu.ui.Splash;

/*import com.basu.data.network.model.SplashRequest;
import com.basu.data.network.model.SplashResponse;*/
import com.basu.ui.splash.SplashMvpInteractor;
import com.basu.ui.splash.SplashMvpView;
import com.basu.ui.splash.SplashPresenter;
import com.basu.utils.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by suhrit on 16/11/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {

    @Mock
    SplashMvpView mMockSplashMvpView;
    @Mock
    SplashMvpInteractor mMockSplashMvpInteractor;

    private SplashPresenter<SplashMvpView, SplashMvpInteractor> mSplashPresenter;
    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mSplashPresenter = new SplashPresenter<>(
                mMockSplashMvpInteractor,
                testSchedulerProvider,
                compositeDisposable);
        mSplashPresenter.onAttach(mMockSplashMvpView);
    }

    @Test
    public void testServerSplashSuccess() {

        /*String email = "dummy@gmail.com";
        String password = "password";

        SplashResponse SplashResponse = new SplashResponse();

        doReturn(Observable.just(SplashResponse))
                .when(mMockSplashMvpInteractor)
                .doServerSplashApiCall(new SplashRequest
                        .ServerSplashRequest(email, password));

        mSplashPresenter.onServerSplashClick(email, password);

        mTestScheduler.triggerActions();

        verify(mMockSplashMvpView).showLoading();
        verify(mMockSplashMvpView).hideLoading();
        verify(mMockSplashMvpView).openMainActivity();*/
    }


    @After
    public void tearDown() throws Exception {
        mSplashPresenter.onDetach();
    }
}
