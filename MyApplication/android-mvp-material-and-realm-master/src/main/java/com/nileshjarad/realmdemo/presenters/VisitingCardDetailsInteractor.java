package com.nileshjarad.realmdemo.presenters;

import android.text.TextUtils;

import com.nileshjarad.realmdemo.models.VisitingCardPOJO;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public class VisitingCardDetailsInteractor {

    private final VisitingCardDetailsPresenter presenterCallBack;

    public VisitingCardDetailsInteractor(VisitingCardDetailsPresenter presenterCallBack) {
        this.presenterCallBack = presenterCallBack;
    }


    public int getTotalNumberOfRow(Realm realm) {
        RealmResults<VisitingCardPOJO> query = realm.where(VisitingCardPOJO.class)
                .findAll();


        return (int) query.max("no").longValue();
    }

    public boolean verifyData(VisitingCardPOJO card) {
        if (TextUtils.isEmpty(card.getName())) {
            presenterCallBack.showErrorForName();
            return false;
        } else if (TextUtils.isEmpty(card.getAddress())) {
            presenterCallBack.showErrorForAddress();
            return false;
        }
        return true;
    }

    public void addNewCard(Realm realm, VisitingCardPOJO card) {
        realm.beginTransaction();
        VisitingCardPOJO visitingCard = realm.createObject(VisitingCardPOJO.class);
        visitingCard.setNo(card.getNo());
        visitingCard.setName(card.getName());
        visitingCard.setAddress(card.getAddress());
        realm.commitTransaction();
    }

    public VisitingCardPOJO getCardDetailForID(Realm realm, int card_id) {
        return realm.where(VisitingCardPOJO.class)
                .equalTo("no", card_id).findFirst();
    }


    public void updateNewCard(Realm realm, VisitingCardPOJO card) {
        VisitingCardPOJO toEdit = realm.where(VisitingCardPOJO.class)
                .equalTo("no", card.getNo()).findFirst();
        realm.beginTransaction();
        toEdit.setName(card.getName());
        toEdit.setAddress(card.getAddress());
        realm.commitTransaction();
    }
}
