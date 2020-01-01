package be_nao.mvvm_base.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import be_nao.mvvm_base.api.random_user.RandomUser
import be_nao.mvvm_base.api.random_user.RandomUserService
import timber.log.Timber
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val randomUserService: RandomUserService
) :
    ViewModel() {

    companion object {
        private const val TAG = "Dash"
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val randomUser = MutableLiveData<RandomUser>()

    private val disposable = CompositeDisposable()
    val errorObserver = MutableLiveData<Throwable>()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    init {
        getRandomUser()
    }


    private fun getRandomUser() {
        randomUserService.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                randomUser.postValue(it)
            }, {
                Timber.d(it.toString())
                errorObserver.postValue(it)
            }).let {
                disposable.add(it)
            }
    }
}
