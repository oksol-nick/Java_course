package org.example.students;

import java.util.LinkedHashMap;
import java.util.Map;

public class CachedAverageAnalytics implements Analytics{

    private final Map<String, Double> cache = new LRUCache<>(2);

    private final AverageAnalytics averageAnalytics;

    public CachedAverageAnalytics(AverageAnalytics averageAnalytics) {
        this.averageAnalytics = averageAnalytics;
    }

    @Override
    public double getAverageForSubjectForAnalytic(String subject) {

//        Double average = cache.get(subject);
//        if(average == null) {
//            average = averageAnalytics.getAverageForSubjectForAnalytic(subject);
//            cache.put(subject, average);
//        }
//
//        return average;

        return cache.computeIfAbsent(subject, averageAnalytics::getAverageForSubjectForAnalytic);
    }
}

class LRUCache<KEY, VALUE> extends LinkedHashMap<KEY, VALUE> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }


}