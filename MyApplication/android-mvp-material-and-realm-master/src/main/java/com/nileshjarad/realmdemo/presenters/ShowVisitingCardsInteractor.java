package com.nileshjarad.realmdemo.presenters;

import android.view.View;

import com.nileshjarad.realmdemo.models.VisitingCardPOJO;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public class ShowVisitingCardsInteractor {


    private final ShowVisitingCardsPresenterImpl showVisitingCardsPresenter;

    public ShowVisitingCardsInteractor(ShowVisitingCardsPresenterImpl showVisitingCardsPresenter) {
        this.showVisitingCardsPresenter = showVisitingCardsPresenter;
    }

    public ArrayList<VisitingCardPOJO> getAllVisitingCards(Realm realm) {
        ArrayList<VisitingCardPOJO> visitingCardPOJOArrayList = new ArrayList<VisitingCardPOJO>();
        RealmResults<VisitingCardPOJO> query = realm.where(VisitingCardPOJO.class)
                .findAll();
        for (VisitingCardPOJO p : query) {
            visitingCardPOJOArrayList.add(p);
        }
        return visitingCardPOJOArrayList;
    }

    public void deleteCard(Realm realm, int card_id,int item_postion) {

        VisitingCardPOJO toEdit = realm.where(VisitingCardPOJO.class)
                .equalTo("no", card_id).findFirst();
        realm.beginTransaction();
        toEdit.removeFromRealm();
        realm.commitTransaction();
        showVisitingCardsPresenter.referesVisitingCardList(item_postion);

    }

}
