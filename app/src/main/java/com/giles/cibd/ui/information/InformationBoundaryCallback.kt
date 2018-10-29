package com.giles.cibd.ui.information

class InformationBoundaryCallback{

}

//class InformationBoundaryCallback @Inject constructor(
//        private val subredditName: String,
//        private val apiService: ApiService,
//        private val handleResponse: (String, ApiService.ListingResponse?) -> Unit,
//        private val ioExecutor: Executor,
//        private val networkPageSize: Int)
//    : PagedList.BoundaryCallback<Repo>() {
//
//    val helper = PagingRequestHelper(ioExecutor)
//    val networkState = helper.createStatusLiveData()
//
//    /**
//     * Database returned 0 items. We should query the backend for more items.
//     */
//    @MainThread
//    override fun onZeroItemsLoaded() {
//        Log.d("GGGG", "onZeroItemsLoaded")
//        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
//            webservice.getTop(
//                    subreddit = subredditName,
//                    limit = networkPageSize)
//                    .enqueue(createWebserviceCallback(it))
//        }
//    }
//
//    /**
//     * User reached to the end of the list.
//     */
//    @MainThread
//    override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
//        Log.d("GGGG", "onItemAtEndLoaded networkPageSize = $networkPageSize")
//        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
//            webservice.getTopAfter(
//                    subreddit = subredditName,
//                    after = itemAtEnd.name,
//                    limit = networkPageSize)
//                    .enqueue(createWebserviceCallback(it))
//        }
//    }
//
//    /**
//     * every time it gets new items, boundary callback simply inserts them into the database and
//     * paging library takes care of refreshing the list if necessary.
//     */
//    private fun insertItemsIntoDb(
//            response: Response<RedditApi.ListingResponse>,
//            it: PagingRequestHelper.Request.Callback) {
//        ioExecutor.execute {
//            Log.d("GGGG", "insertItemsIntoDb 111")
//            handleResponse(subredditName, response.body())
//            it.recordSuccess()
//        }
//    }
//
//    override fun onItemAtFrontLoaded(itemAtFront: RedditPost) {
//        // ignored, since we only ever append to what's in the DB
//        Log.d("GGGG", "onItemAtFrontLoaded")
//    }
//
//    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
//            : Callback<RedditApi.ListingResponse> {
//        return object : Callback<RedditApi.ListingResponse> {
//            override fun onFailure(
//                    call: Call<RedditApi.ListingResponse>,
//                    t: Throwable) {
//                Log.d("GGGG", "onFailure")
//                it.recordFailure(t)
//            }
//
//            override fun onResponse(
//                    call: Call<RedditApi.ListingResponse>,
//                    response: Response<RedditApi.ListingResponse>) {
//                Log.d("GGGG", "onResponse")
//                insertItemsIntoDb(response, it)
//            }
//        }
//    }
//}