/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junkyarddog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author rich
 */
public class Junkyarddog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        
        ArrayList<String> diagnoses = new ArrayList<>();
        //let's overepresent some variables to make that simulated data more interesting
        diagnoses.add("epilepsy");
        diagnoses.add("epilepsy");
        diagnoses.add("epilepsy");        
        diagnoses.add("lymphoma");
        diagnoses.add("hepatitis");
        diagnoses.add("hepatitis");        
        diagnoses.add("renal failure");
        
        ArrayList<String> gender = new ArrayList<>();
        gender.add("male");
        gender.add("male");        
        gender.add("female");
        

        
     
        for (int i=0;i<600;i++){
            
            
            sb.append("update process_source_new set ");
            sb.append("patient_id = ").append(i).append(",");
            String ageInt = String.valueOf(rand.nextInt(99));
            if (ageInt.length()==1){
                ageInt = ageInt +10;
            }
            sb.append("dob = \'13-jul-"+ageInt+"\', ");
            String startspellInt = String.valueOf(rand.nextInt(28));
            if (startspellInt.length()==1){
                startspellInt = 0+startspellInt;
            }            
            if (startspellInt.startsWith("00")){
                startspellInt = "01";
            } 
            Integer endSpell = Integer.valueOf(startspellInt);

            
            
            endSpell = endSpell+ rand.nextInt(25);
            
            String endspellSt;
            
            if(endSpell>31){
                
                
                endspellSt = "spell_end = \'"+(endSpell-31)+"-FEB-07\',";
            }else{
                endspellSt = "spell_end = \'"+endSpell+"-JAN-07\',";
            }
            
            
            sb.append("spell_start = \'"+startspellInt+"-JAN-07\',");
            sb.append(endspellSt);            
            sb.append("diagnosis = \'" +diagnoses.get(rand.nextInt(7))+"\', ");
            sb.append("gender = \'" + gender.get(rand.nextInt(3))+"\' ,");
            sb.append("medication = \'" + buildMedicationJson()+"\'");
            
            sb.append(" where  id in (select id from process_source_new where patient_id is not null order by random() limit 50) ");
            sb.append(";\n");
                                                                                           
            
        }
        
        System.out.print(sb.toString());
        
        
        
        
        
    }

    private static String buildMedicationJson() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> meds = new ArrayList<>();
        meds.add("clozapine");
        meds.add("paracetamol");
        meds.add("tamoxifen");
        meds.add("tamoxifen");        
        meds.add("simvastatin");
        meds.add("simvastatin");
        meds.add("simvastatin");        
        meds.add("rosuvastatin");
        meds.add("AZD2846");
        meds.add("ibuprofen");
        meds.add("clonazepam");
        meds.add("clonazepam");
        meds.add("clonazepam");
        meds.add("ethosuximide");
        meds.add("lacosamide");
        meds.add("lamotrigine");
        meds.add("primidone");
        meds.add("ethosuximide");
        meds.add("Anastrozole");
        meds.add("ethosuximide");
        meds.add("ethosuximide");        
        meds.add("ethosuximide");
        meds.add("ethosuximide");
        meds.add("ethosuximide");        
        
        sb.append("{");
        HashSet<String> set = new HashSet<>();
        Random medRandom = new Random();
        int i =medRandom.nextInt(9); 
        
        for (int ij = 0; ij<=i;ij++){
            set.add(meds.get(ij));
        }   
        Iterator setIt = set.iterator();
        
        while(setIt.hasNext()){
            sb.append("\""+setIt.next()+"\"");
            if(setIt.hasNext()){
                sb.append(",");
            }
        }
        sb.append("}");        
        return sb.toString();        
    }    
}
