package com.nileshjarad.realmdemo.views.activities.views;

import com.nileshjarad.realmdemo.models.VisitingCardPOJO;

import java.util.ArrayList;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public interface ShowVisitingCardView {

    void showAllVisitingCards(ArrayList<VisitingCardPOJO> allVisitingCards);

    void showVisitingCardScreen();

    void showVisitingCardScreenForUpdate(int item_id);

    void refereshRecyclerView(int postion);
}
