package com.nd.hy.ele.idpmodule.cache;

public interface ICache<K, V> {

    /**
     * put the KV pair into cache
     *
     * @param key
     * @param value
     * @return the previous value of the key
     */
    V put(K key, V value);

    /**
     * get the value by key
     *
     * @param key
     * @return the value of the key
     */
    V get(K key);

    /**
     * get the value by key, result defaultValue if the return value is null
     *
     * @param key
     * @param defaultValue
     * @return the value of the key
     */
    V get(K key, V defaultValue);

    /**
     * remove the KV pair
     *
     * @param key
     * @return the previous value of the key
     */
    V remove(K key);

    /**
     * clear the whole cache
     */
    void clear();

    /**
     * return whether beyond the interval
     *
     * @param interval
     * @return
     * @Deprecated
     */
    @Deprecated
    boolean isOutOfDate(K key, long interval);
}
