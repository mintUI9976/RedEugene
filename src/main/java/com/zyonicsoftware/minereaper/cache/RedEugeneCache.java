/*
 *
 *  * Copyright (c) 2021. Zyonic Software - Niklas Griese
 *  * This File, its contents and by extention the corresponding project is property of Zyonic Software and may not be used without explicit permission to do so.
 *  *
 *  * contact(at)zyonicsoftware.com
 *
 */

package com.zyonicsoftware.minereaper.cache;

import com.zyonicsoftware.minereaper.objects.EugeneJob;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Niklas Griese
 */

public class RedEugeneCache {

    /**
     * contains current open jobs
     */
    private final static Map<String, EugeneJob> liveEugeneJobMap = new HashMap<>();
    /**
     * contains all closed or overwrite jobs
     */
    private final static Map<String, EugeneJob> cachedEugeneJobMap = new HashMap<>();

    /**
     * @param eugeneJobName used to identify the job
     * @param eugeneJob     = value
     */
    public static void put(@NotNull final String eugeneJobName, @NotNull final EugeneJob eugeneJob) {
        RedEugeneCache.liveEugeneJobMap.put(eugeneJobName, eugeneJob);
    }

    /**
     * @param eugeneJobName used to identify the job
     *                      the identified job will be removed of the live map and will be add or merge on the cached map
     */

    public static void remove(@NotNull final String eugeneJobName) {
        if (!RedEugeneCache.cachedEugeneJobMap.containsKey(eugeneJobName)) {
            RedEugeneCache.cachedEugeneJobMap.put(eugeneJobName, RedEugeneCache.liveEugeneJobMap.remove(eugeneJobName));
        } else {
            RedEugeneCache.cachedEugeneJobMap.merge(eugeneJobName, RedEugeneCache.liveEugeneJobMap.remove(eugeneJobName), (eugeneJob, eugeneJob2) -> eugeneJob2);
        }

    }

    /**
     * @param eugeneJobName used to identify the job
     * @return an optional of the EugeneJob and result can be nullable
     */

    public static Optional<EugeneJob> getLive(@NotNull final String eugeneJobName) {
        return Optional.ofNullable(RedEugeneCache.liveEugeneJobMap.get(eugeneJobName));
    }

    /**
     * @param eugeneJobName used to identify the job
     * @return an optional of the EugeneJob and result can be nullable
     */

    public static Optional<EugeneJob> getCached(@NotNull final String eugeneJobName) {
        return Optional.ofNullable(RedEugeneCache.cachedEugeneJobMap.get(eugeneJobName));
    }

    /**
     * @return a collection of all live EugeneJobs
     */

    public static Collection<EugeneJob> getAllLiveEugeneJobs() {
        return RedEugeneCache.liveEugeneJobMap.values();
    }

    /**
     * @return a collection of all cached EugeneJobs
     */

    public static Collection<EugeneJob> getAllCachedEugeneJobs() {
        return RedEugeneCache.cachedEugeneJobMap.values();
    }
}
