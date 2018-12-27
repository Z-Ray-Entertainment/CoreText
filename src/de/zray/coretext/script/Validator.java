/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zray.coretext.script;

import de.zray.coretext.exceptions.DublicateSyntaxRuleException;
import de.zray.coretext.exceptions.SyntaxException;
import de.zray.coretext.syntax.ClipRule;
import de.zray.coretext.syntax.SemicolonRule;
import de.zray.coretext.syntax.StateRule;
import de.zray.coretext.syntax.StringRule;
import de.zray.coretext.syntax.SyntaxRule;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Vortex Acherontic
 */
public class Validator {
    private List<SyntaxRule> rules = new LinkedList<>();
    
    public Validator(boolean defaultRules) throws DublicateSyntaxRuleException{
        if(defaultRules){
            initDefaultRules();
        }
    }
    
    private void initDefaultRules() throws DublicateSyntaxRuleException{
        addRule(new ClipRule());
        addRule(new SemicolonRule());
        addRule(new StringRule());
        addRule(new StateRule());
    }
    
    public void checkSyntax(String input) throws SyntaxException{
        rules.stream().forEach((tmp) -> {
            tmp.reset();
        });
        
        for(int i = 0; i < input.length(); i++){
            String curChar = input.substring(i, i+1);
            for(SyntaxRule tmp : rules){
                tmp.check(curChar);
            }
        }
        for(SyntaxRule rule : rules){
            rule.endOfScript();
        }
    }
    
     public void addRule(SyntaxRule rule) throws DublicateSyntaxRuleException{
        if(rules != null){
            if(rules.contains(rule)){
                throw new DublicateSyntaxRuleException("Dublicate Rule.");
            }
        }
        rules.add(rule);
    }
}
