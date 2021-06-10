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

public class RedEugeneCache {

    private final static Map<String, EugeneJob> eugeneJobMap = new HashMap<>();
    private final static Map<String, EugeneJob> cachedEugeneJobMap = new HashMap<>();

    public static void put(@NotNull final String eugeneJobName, @NotNull final EugeneJob eugeneJob) {
        RedEugeneCache.eugeneJobMap.put(eugeneJobName, eugeneJob);
    }

    public static void remove(@NotNull final String eugeneJobName) {
        if (!RedEugeneCache.cachedEugeneJobMap.containsKey(eugeneJobName)) {
            RedEugeneCache.cachedEugeneJobMap.put(eugeneJobName, RedEugeneCache.eugeneJobMap.remove(eugeneJobName));
        } else {
            RedEugeneCache.cachedEugeneJobMap.merge(eugeneJobName, RedEugeneCache.eugeneJobMap.remove(eugeneJobName), (eugeneJob, eugeneJob2) -> eugeneJob2);
        }

    }

    public static Optional<EugeneJob> getLive(@NotNull final String eugeneJobName) {
        return Optional.ofNullable(RedEugeneCache.eugeneJobMap.get(eugeneJobName));
    }

    public static Optional<EugeneJob> getCached(@NotNull final String eugeneJobName) {
        return Optional.ofNullable(RedEugeneCache.cachedEugeneJobMap.get(eugeneJobName));
    }

    public static Collection<EugeneJob> getAllLiveEugeneJobs() {
        return RedEugeneCache.eugeneJobMap.values();
    }

    public static Collection<EugeneJob> getAllCachedEugeneJobs() {
        return RedEugeneCache.cachedEugeneJobMap.values();
    }
}
