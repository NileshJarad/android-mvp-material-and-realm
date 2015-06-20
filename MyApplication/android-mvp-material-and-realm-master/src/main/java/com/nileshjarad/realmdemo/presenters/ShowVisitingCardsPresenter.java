package com.nileshjarad.realmdemo.presenters;

import io.realm.Realm;

/**
 * Created by admin on 19-06-2015.
 */
public interface ShowVisitingCardsPresenter {
    void getVisitingCards(Realm realm);

    void startVisitingCardActivity();

    void startVisitingCardUpdateActivity(int item_id);

    void referesVisitingCardList(int position);

    void detletCardFromRealm(Realm realm,int card_id,int position);
}
