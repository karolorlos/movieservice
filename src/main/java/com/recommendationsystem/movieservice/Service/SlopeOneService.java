package com.recommendationsystem.movieservice.Service;


import com.recommendationsystem.movieservice.Model.Movie;
import com.recommendationsystem.movieservice.Model.Prediction;
import com.recommendationsystem.movieservice.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.zip.DeflaterOutputStream;

@Service
public class SlopeOneService {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RatingService ratingService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private RatingRepository ratingRepository;





        Map<Long, Map<Long, Double>> diffMatrix;
        Map<Long, Map<Long, Integer>> freqMatrix;
        static Long[] mAllItems = new Long[]{1L,2L,3L,4L,5L,6L,7L,8L,9L};




    public void buildDiffMatrix(Map<Long, Map<Long, Double>> mData)
    {

        diffMatrix = new HashMap<>();
        freqMatrix = new HashMap<>();


        //dla kazdego uzytkownika w bazie danych
        for (Map<Long, Double> user : mData.values())
        {
            //dla kazdej pary Idfilmu i ocena wykonaj


            for (Map.Entry<Long, Double> entry : user.entrySet())
            {
                //id filmu
                Long i1 = entry.getKey();
                //ocena filmu
                double r1 = entry.getValue();

                //jesli macierze nie zawieraja klucza idFilmu dodaj
                if (!diffMatrix.containsKey(i1))
                {
                    diffMatrix.put(i1, new HashMap<>());
                    freqMatrix.put(i1, new HashMap<>());
                }

                //petla w petli, sprawdzamy roznice dla drugiej pary idFilmu i oceny
                for (Map.Entry<Long, Double> entry2 : user.entrySet())
                {
                    Long i2 = entry2.getKey();
                    double r2 = entry2.getValue();

                    int cnt = 0;

                    if (freqMatrix.get(i1).containsKey(i2)) cnt = freqMatrix.get(i1).get(i2);
                    double diff = 0.0;


                    if (diffMatrix.get(i1).containsKey(i2)) diff = diffMatrix.get(i1).get(i2);
                    //oblicz nowa roznice
                    double new_diff = r1 - r2;

                    //zaaktualizuj czestosc w macierzy
                    freqMatrix.get(i1).put(i2, cnt + 1);
                    //dodaj nowa roznice do macierzy
                    diffMatrix.get(i1).put(i2, diff + new_diff);
                }
            }
        }
        //korekcja roznic w diffMatrix poprzez dzielenie starej wartosci przez czestosc
        for (Long j : diffMatrix.keySet())
        {
            for (Long i : diffMatrix.get(j).keySet())
            {
                Double oldvalue = diffMatrix.get(j).get(i);
                int count = freqMatrix.get(j).get(i).intValue();
                diffMatrix.get(j).put(i, oldvalue / count);
            }
        }




    }



    public Map<Long, Double> predict( Map<Long, Double> user)
    {
        HashMap<Long, Double> predictions = new HashMap<>();
        HashMap<Long, Integer> frequencies = new HashMap<>();
        for (Long j : diffMatrix.keySet())
        {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Long j : user.keySet())
        {
            for (Long k : diffMatrix.keySet())
            {
                try
                {
                    Double newval = (diffMatrix.get(k).get(j) + user.get(j)) * freqMatrix.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + newval);
                    frequencies.put(k, frequencies.get(k) + freqMatrix.get(k).get(j).intValue());
                } catch (NullPointerException e)
                {}
            }
        }
        HashMap<Long, Double> cleanpredictions = new HashMap<>();
        for (Long j : predictions.keySet())
        {
            if (frequencies.get(j) > 0)
            {
                cleanpredictions.put(j, predictions.get(j) / frequencies.get(j).intValue());
            }
        }
        for (Long j : user.keySet())
        {
            cleanpredictions.put(j, user.get(j));
        }
        return cleanpredictions;
    }


    public void printData(Map<Long, Map<Long, Double>> mData )
    {
        for (Long user : mData.keySet())
        {
            System.out.println(user);
            print(mData.get(user));
        }
        for (int i = 0; i < mAllItems.length; i++)
        {
            System.out.print("\n" + mAllItems[i] + ":");
            printMatrixes(diffMatrix.get(mAllItems[i]), freqMatrix.get(mAllItems[i]));
        }
    }


    private void printMatrixes(Map<Long, Double> ratings, Map<Long, Integer> frequencies)
    {
        for (int j = 0; j < mAllItems.length; j++)
        {
            System.out.format("%10.3f", ratings.get(mAllItems[j]));
            System.out.print(" ");
            System.out.format("%10d", frequencies.get(mAllItems[j]));
        }
        System.out.println();
    }

    public static void print(Map<Long, Double> user)
    {
        for (Long j : user.keySet())
        {
            System.out.println(" " + j + " --> " + user.get(j).floatValue());
        }
    }

    public static void printBestOption(Map<Long, Double> user){
        float maxValue = 0;
        Long bestOption = null;
        for (Long j: user.keySet()
                ) {
            if (user.get(j).floatValue()>maxValue){
                maxValue = user.get(j).floatValue();
                bestOption = j;
            }
        }
        System.out.println("Recommend movie id : " + bestOption);
    }

    public List<Prediction> findAllPredictions(Map<Long, Double> user, Long userId){
        List<Prediction> listOfPredictions = new ArrayList<>();

        for (Map.Entry<Long, Double> entry: user.entrySet()
             ) {
                    if (!(ratingRepository.existsRatingByMovieIdAndUserId(entry.getKey(),userId))) {
                        Prediction prediction = new Prediction();
                        prediction.setMovieId(entry.getKey());
                        prediction.setPredictionRating(entry.getValue());
                        prediction.setTitle(movieService.findTitleByMovieId(entry.getKey()));
                        listOfPredictions.add(prediction);
                    }

            }

            listOfPredictions.sort(Comparator.comparingDouble(Prediction::getPredictionRating).reversed());




        return listOfPredictions;
    }
//    public Double findBestPrediction(List<Prediction> listAllPredictions, Map<Long, Double> user){
//        listAllPredictions = findAllPredictions(user);
//        Double bestPredictionRating = 0.0;
//        for (Prediction prediction: listAllPredictions
//             ) {
//            if (prediction.getPredictionRating() > bestPredictionRating){
//                bestPredictionRating = prediction.getPredictionRating();
//
//            }
//        }
//        return bestPredictionRating;
//    }
//

}
