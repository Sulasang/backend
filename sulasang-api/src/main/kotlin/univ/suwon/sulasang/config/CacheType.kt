package univ.suwon.sulasang.config


enum class CacheType(
    val cacheName: String,
    val expiredAfterWrite: Long,
    val maximumSize: Long
) {
    WEEKLY_DIET(WEEKLY_DIET_CACHE_NAME, CACHE_LIFE_TIME, MAXIMUM_CACHE_SIZE),
    DATE_TYPE_DIET(DATE_TYPE_CACHE_NAME, CACHE_LIFE_TIME, MAXIMUM_CACHE_SIZE)
}

const val WEEKLY_DIET_CACHE_NAME = "weekly-diet"
const val DATE_TYPE_CACHE_NAME = "date-type-diet"

const val CACHE_LIFE_TIME = 10 * 60L
const val MAXIMUM_CACHE_SIZE = 1000L
