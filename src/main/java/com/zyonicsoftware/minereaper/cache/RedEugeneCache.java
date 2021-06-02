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
        RedEugeneCache.cachedEugeneJobMap.put(eugeneJobName, eugeneJob);
    }

    public static void remove(@NotNull final String eugeneJobName) {
        RedEugeneCache.eugeneJobMap.remove(eugeneJobName);
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
