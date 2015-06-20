package com.nileshjarad.realmdemo.presenters;

import com.nileshjarad.realmdemo.models.VisitingCardPOJO;

import io.realm.Realm;

/**
 * Created by admin on 18-06-2015.
 */
public interface VisitingCardDetailsPresenter {
    void getNextId(Realm realm);

    void saveNewCard(Realm realm, VisitingCardPOJO card);

    void showErrorForName();

    void showErrorForAddress();

    boolean verifyContent(VisitingCardPOJO card);

    void getCard(Realm realm, int card_id);

    void updateCard(Realm realm, VisitingCardPOJO card);
}
