package com.nileshjarad.realmdemo.presenters;

import com.nileshjarad.realmdemo.models.VisitingCardPOJO;
import com.nileshjarad.realmdemo.views.activities.views.VisitingCardDetailsView;

import io.realm.Realm;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public class VisitingCardDetailsPresenterImpl implements VisitingCardDetailsPresenter {

    private final VisitingCardDetailsInteractor interactor;
    private final VisitingCardDetailsView view;

    public VisitingCardDetailsPresenterImpl(VisitingCardDetailsView view) {
        this.interactor = new VisitingCardDetailsInteractor(this);
        this.view = view;
    }

    @Override
    public void getNextId(Realm realm) {
        view.setNextId(interactor.getTotalNumberOfRow(realm));
    }

    @Override
    public void saveNewCard(Realm realm, VisitingCardPOJO card) {
        interactor.addNewCard(realm, card);
    }

    @Override
    public void showErrorForName() {
        view.setErrorForName();
    }

    @Override
    public void showErrorForAddress() {
        view.setErrorForAddress();
    }

    @Override
    public boolean verifyContent(VisitingCardPOJO card) {
        return interactor.verifyData(card);
    }

    @Override
    public void getCard(Realm realm, int card_id) {
        view.showCardToEdit(interactor.getCardDetailForID(realm, card_id));
    }

    @Override
    public void updateCard(Realm realm, VisitingCardPOJO card) {
        interactor.updateNewCard(realm, card);
    }
}
