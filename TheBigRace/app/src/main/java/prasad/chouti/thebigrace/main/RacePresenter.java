package prasad.chouti.thebigrace.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import prasad.chouti.thebigrace.models.Contestants;
import prasad.chouti.thebigrace.retrofit.RestClient;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Date   : 3/29/17
 * Time   : 9:04 AM
 */

public class RacePresenter {

    private RaceView raceView;
    private RestClient restClient = new RestClient();

    public RacePresenter(RaceView raceView) {
        this.raceView = raceView;
    }


    public void getRaceStats() {
        raceView.showProgress();
        ;
        final Observable<Contestants> raceContestants = restClient.createNewApi().getRaceContestants();

        final Observable<List<Contestants.Runners>> raceStatsAsObservable = getRaceStatsAsObservable(raceContestants);

        raceStatsAsObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Contestants.Runners>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        raceView.hideProgress();
                        e.printStackTrace();
                        raceView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Contestants.Runners> contestants) {

                        raceView.showRaceStats(contestants);
                        raceView.hideProgress();
                    }
                });


    }


    private Observable<List<Contestants.Runners>> getRaceStatsAsObservable(Observable<Contestants> raceContestants) {
        return raceContestants.flatMap(new Func1<Contestants, Observable<List<Contestants.Runners>>>() {
            @Override
            public Observable<List<Contestants.Runners>> call(Contestants contestants) {
                return Observable.from(contestants.getRunners()).toSortedList(new Func2<Contestants.Runners, Contestants.Runners, Integer>() {
                    @Override
                    public Integer call(Contestants.Runners runners, Contestants.Runners runners2) {
                        return runners.getTime() - runners2.getTime();
                    }
                });
            }
        }).doOnNext(new Action1<List<Contestants.Runners>>() {
            @Override
            public void call(List<Contestants.Runners> runnerses1) {
                HashMap<Integer, List<Contestants.Runners>> listHashMap = new HashMap<Integer, List<Contestants.Runners>>();
                for (Contestants.Runners contestant : runnerses1) {


                    List<Contestants.Runners> runnerses;
                    int mapIndex = 0;

                    if (isBetween0to15(contestant.getAge())) {
                        mapIndex = 0;
                        if (!listHashMap.containsKey(mapIndex)) {
                            runnerses = new ArrayList<>();

                        } else {
                            runnerses = listHashMap.get(mapIndex);
                        }

                    } else if (isBetween16to29(contestant.getAge())) {
                        mapIndex = 1;
                        if (!listHashMap.containsKey(mapIndex)) {
                            runnerses = new ArrayList<>();

                        } else {
                            runnerses = listHashMap.get(mapIndex);
                        }

                    } else {
                        mapIndex = 2;
                        if (!listHashMap.containsKey(mapIndex)) {
                            runnerses = new ArrayList<>();

                        } else {
                            runnerses = listHashMap.get(mapIndex);
                        }

                    }

                    contestant.setRank(runnerses.size() + 1);
                    runnerses.add(contestant);
                    listHashMap.put(mapIndex, runnerses);
                }
            }
        });
    }

    private boolean isBetween0to15(int age) {
        return age >= 0 && age <= 15;
    }

    private boolean isBetween16to29(int age) {
        return age >= 16 && age <= 29;
    }

    private boolean isAbove30(int age) {
        return age >= 30;
    }
}
