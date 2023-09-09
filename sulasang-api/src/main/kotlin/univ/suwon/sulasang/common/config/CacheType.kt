package univ.suwon.sulasang.common.config



enum class CacheType(
    val cacheName: String,
    val expiredAfterWrite: Long,
    val maximumSize: Long
) {
    WEEKLY_DIET("weekly-diet", 6000, 1000),
    DATE_TYPE_DIET("date-type-diet", 6000, 1000)
}
