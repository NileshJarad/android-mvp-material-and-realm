package com.nileshjarad.realmdemo.views.activities.views;

import com.nileshjarad.realmdemo.models.VisitingCardPOJO;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public interface VisitingCardDetailsView {
    void setNextId(int id);

    void setErrorForName();

    void setErrorForAddress();

    void showCardToEdit(VisitingCardPOJO cardDetailForID);
}
