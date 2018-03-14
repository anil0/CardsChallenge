package co.uk.anil.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.uk.anil.model.Card;
import co.uk.anil.model.ValidationResult;

/**
 * Created by anil on 12/03/2018.
 */

@Controller
public class HomeController
{
    private final DeckController deckController = DeckController.getInstance();
    private ArrayList<Card> deck = deckController.setupDeck();
    private ArrayList<Card> hand = new ArrayList<>();


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcome( Map<String, Object> model )
    {
        deckController.shuffleDeck( deck );

        model.put( "deckCount", deck.size() );
        model.put( "hand", hand );

        return "index";
    }


    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String submitCardsToDraw( @RequestParam(value = "numOfCards") String numOfCards,
                                     RedirectAttributes redirectAttributes )
    {
        ValidationResult validationResult = ValidationResult.validateCardsDrawn( deck.size(), numOfCards );

        //draw cards from deck
        deckController.drawAndRemoveCardFromDeck( validationResult.getNumOfCardsToDraw(), hand, deck );

        if( validationResult.getNumOfCardsToDraw() != 0 )
        {
            redirectAttributes.addFlashAttribute( "success", "You drew " + validationResult.getNumOfCardsToDraw() + " card(s)" );
        }

        redirectAttributes.addFlashAttribute( "numOfCards", validationResult.getNumOfCardsToDraw() );
        redirectAttributes.addFlashAttribute( "hand", hand );

        if( validationResult.getErrors() != null )
        {
            redirectAttributes.addFlashAttribute( "errors", validationResult.getErrors() );
        }

        return "redirect:/home";
    }


    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public String sortCards( RedirectAttributes redirectAttributes )
    {
        deckController.sortCards( hand );

        //sort and then pass it back to the view
        redirectAttributes.addFlashAttribute( "success", "Your hand has been sorted" );
        redirectAttributes.addFlashAttribute( "hand", hand );

        return "redirect:/home";
    }


    @RequestMapping(value = "/shuffle", method = RequestMethod.POST)
    public String shuffleCards( RedirectAttributes redirectAttributes )
    {
        deckController.shuffleDeck( deck );

        redirectAttributes.addFlashAttribute( "success", "The deck was shuffled" );
        redirectAttributes.addFlashAttribute( "hand", hand );

        return "redirect:/home";
    }


    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String resetDeckAndHand( RedirectAttributes redirectAttributes )
    {
        //reset
        deck = deckController.setupDeck();
        hand = new ArrayList<>();

        redirectAttributes.addFlashAttribute( "success", "The deck and hand were reset" );

        return "redirect:/home";
    }
}
