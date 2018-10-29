package com.giles.cibd.data

//@Singleton
//class RepoRepository @Inject
//constructor(private val repoDao: RepoDao, private val ApiService: ApiService) {
//
////    fun search(query: String): LiveData<Resource<List<Repo>>> {
////        return object : NetworkBoundResource<List<Repo>, RepoSearchResponse>() {
////            override fun loadFromDb(): LiveData<List<Repo>> {
////                return Transformations.switchMap(repoDao.search(query)) { searchData ->
////                    if (searchData == null) {
////                        AbsentLiveData.create()
////                    } else {
////                        repoDao.loadOrdered(searchData.repoIds!!)
////                    }
////                }
////            }
////
////            override fun shouldFetch(data: List<Repo>?): Boolean {
////                return data == null
////            }
////
////            override fun createCall(): LiveData<ApiResponse<RepoSearchResponse>> {
////                return ApiService.searchRepos(query)
////            }
////
////            override fun saveCallResult(item: RepoSearchResponse) {
////                val repoIds = item.repoIds
////                val repoSearchResult = RepoSearchResult(query, repoIds, item.total)
////
////                repoDao.insertRepos(item.items)
////                repoDao.insert(repoSearchResult)
////            }
////        }.asLiveData()
////    }
//}