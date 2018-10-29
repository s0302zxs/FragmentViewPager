package com.giles.cibd.ui

class RepoViewModel{

}

//class RepoViewModel @Inject
//constructor(private val mInformationRepository: InformationRepository) : ViewModel() {
//    private val isLoading = ObservableBoolean(false)
//    private val compositeDisposable = CompositeDisposable()
//    private val query = MutableLiveData<String>()
//
////    val networkState : LiveData<NetworkState> = {mInformationRepository?.getNetworkState()}
//
////    val repos: LiveData<Resource<PagedList<Repo>>>
//    val repos: LiveData<PagedList<Repo>>
//
//    init {
//        repos = Transformations.switchMap(query) { userInput ->
//            Timber.d("asa")
////            if (userInput == null || userInput.isEmpty()) {
////                AbsentLiveData.create()
////            } else {
////                repository.search(userInput)
////            }
//            mInformationRepository.search(userInput, 1, 10)
////            return@switchMap null
//        }
//    }
//
//    fun searchRepo(userInput: String) {
//        Timber.d("searchRepo userInput = $userInput")
//        query.value = userInput
//    }
//
////    internal fun getRepos(): LiveData<PagedList<Repo>> {
////        return repos
////    }
//
//    fun getNetworkState(): LiveData<NetworkState> {
//        return mInformationRepository?.getNetworkState()
//    }
//
//}