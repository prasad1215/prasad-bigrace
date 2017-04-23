package prasad.chouti.thebigrace.main;

import java.util.List;

import prasad.chouti.thebigrace.models.Contestants;

/**
 * Date   : 3/29/17
 * Time   : 9:03 AM
 */

public interface RaceView {

    void showRaceStats(List<Contestants.Runners> contestants);

    void showProgress();

    void hideProgress();

    void onError(String message);
}
