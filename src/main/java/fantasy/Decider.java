package fantasy;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * Created by trevor on 10/10/15.
 */
public class Decider {
    public static void main(String[] args) throws IOException {
        String baseDirectory = "/home/trevor/fantasy/week5/";

        String defenseFile = baseDirectory + "Def.txt";
        String fanDuelFile = baseDirectory + "FanDuel-NFL-2015-10-11-13183-players-list.csv";
        String flexRankingsFile = baseDirectory + "FlexRankings.txt";
        String kickFile = baseDirectory + "Kick.txt";
        String qbFile = baseDirectory + "QB.txt";

        Map<String, Collection<EspnRanking>> rankings = new HashMap<>();

        Collection<EspnRanking> flexRankings = readTheLines(flexRankingsFile, true);
        rankings.put("FLEX", flexRankings);
        Collection<EspnRanking> defenseRankings = readTheLines(defenseFile, false);
        rankings.put("DEF", defenseRankings);
        Collection<EspnRanking> kickerRankings = readTheLines(kickFile, false);
        rankings.put("KICK", kickerRankings);
        Collection<EspnRanking> qbrankings = readTheLines(qbFile, false);
        rankings.put("QB", qbrankings);

        Map<String,Map<String, EspnRanking>> espnPlayerMap = new HashMap<>();

        for (Map.Entry<String, Collection<EspnRanking>> entry : rankings.entrySet()) {
            String key = entry.getKey();
            Map<String, EspnRanking> espnRankingMap = new HashMap<>();
            for (EspnRanking espnRanking : entry.getValue()) {
                espnRankingMap.put(espnRanking.getPlayerName(), espnRanking);
            }
            espnPlayerMap.put(key, espnRankingMap);
        }

        Collection<FanDuelPlayer> fanDuelPlayers = parseFile(fanDuelFile);
        Map<String, FanDuelPlayer> fanDuelPlayerMap = new HashMap<>();
        for (FanDuelPlayer fanDuelPlayer : fanDuelPlayers) {
            fanDuelPlayerMap.put(fanDuelPlayer.getFullName(), fanDuelPlayer);
        }

        Collection<DeciderMetaData> deciderMetaDatas = new ArrayList<>();

        for (Map<String, EspnRanking> espnRankingMap : espnPlayerMap.values()) {

            for (Map.Entry<String, EspnRanking> espnEntry : espnRankingMap.entrySet()) {
                String s = espnEntry.getKey();
                EspnRanking ranking = espnEntry.getValue();

                if (!fanDuelPlayerMap.containsKey(s)) {
                    System.out.println("Missing " + s);
                } else {
                    DeciderMetaData deciderMetaData = new DeciderMetaData();
                    deciderMetaData.setName(s);
                    deciderMetaData.setEspnRanking(ranking);
                    FanDuelPlayer fanDuelPlayer = fanDuelPlayerMap.get(s);
                    deciderMetaData.setFanDuelPlayer(fanDuelPlayer);
                    deciderMetaData.setPosition(fanDuelPlayer.getPosition());
                    deciderMetaData.setSalary(fanDuelPlayer.getSalary());
                    deciderMetaDatas.add(deciderMetaData);
                }
            }
        }

        int max = 60000;

        Map<String, Collection<DeciderMetaData>> positionMap = new HashMap<>();
        for (DeciderMetaData deciderMetaData : deciderMetaDatas) {
            if(!positionMap.containsKey(deciderMetaData.getPosition())) {
                Collection<DeciderMetaData> collection = new ArrayList<>();
                collection.add(deciderMetaData);
                positionMap.put(deciderMetaData.getPosition(), collection);
            } else {
                Collection<DeciderMetaData> metaDataCollection = positionMap.get(deciderMetaData.getPosition());
                metaDataCollection.add(deciderMetaData);
            }
        }

        Roster roster = new Roster();

        DeciderMetaData qb = findNeedleInHayStack(7000, 7500, positionMap.get("QB"));
        roster.setQuarterback(qb);

        DeciderMetaData rb1 = findNeedleInHayStack(6000, 9000, positionMap.get("RB"));
        roster.setRunningBack1(rb1);

        DeciderMetaData rb2 = findNeedleInHayStack(6000, 7500, positionMap.get("RB"),rb1);
        roster.setRunningBack2(rb2);

        DeciderMetaData wr1 = findNeedleInHayStack(7000, 7500, positionMap.get("WR"));
        roster.setWr1(wr1);

        DeciderMetaData wr2 = findNeedleInHayStack(6000, 6500, positionMap.get("WR"),wr1);
        roster.setWr2(wr2);

        DeciderMetaData wr3 = findNeedleInHayStack(5500, 6300, positionMap.get("WR"),wr1,wr2);
        roster.setWr3(wr3);

        DeciderMetaData te = findNeedleInHayStack(5000, 8000, positionMap.get("TE"));
        roster.setTe(te);

        DeciderMetaData k = findNeedleInHayStack(4500, 5500, positionMap.get("K"));
        roster.setK(k);

        DeciderMetaData def = findNeedleInHayStack(3500, 5000, positionMap.get("D"));
        roster.setDef(def);

        roster.printSummary();
    }

    public static DeciderMetaData findNeedleInHayStack(int from, int to, Collection<DeciderMetaData> deciderMetaDatas,DeciderMetaData... exclude){
        List<DeciderMetaData> sortedDeciderList = new ArrayList<>(deciderMetaDatas);
        sortedDeciderList.sort((d1,d2)->d1.getEspnRanking().getPlayerRanking().compareTo(d2.getEspnRanking().getPlayerRanking()));

        for (DeciderMetaData deciderMetaData : exclude) {
            sortedDeciderList.remove(deciderMetaData);
        }

        return sortedDeciderList.stream().filter(x ->
        {
            Integer salary = x.getFanDuelPlayer().getSalary();

            return salary != null && salary >= from && salary <= to;
        }).findFirst().orElse(null);
    }

    public static Collection<FanDuelPlayer> parseFile(String fileName) throws IOException {
        Collection<FanDuelPlayer> players = new ArrayList<>();

        Reader in = new FileReader(fileName);
        Iterable<CSVRecord> records = CSVFormat.newFormat(',').parse(in);
        for (CSVRecord record : records) {
            String salary = record.get(6).replace("\"","");
            FanDuelPlayer fanDuelPlayer = new FanDuelPlayer(record.get(2),record.get(3), Integer.valueOf(salary),record.get(10),record.get(1));
            players.add(fanDuelPlayer);
        }

        return players;
    }

    public static Collection<EspnRanking> readTheLines(String fileName,boolean hasPositionRankings) throws IOException {
        Reader in = new FileReader(fileName);
        Iterable<CSVRecord> records = CSVFormat.newFormat('\t').parse(in);
        Collection<EspnRanking> espnRankings = new ArrayList<>();
        for (CSVRecord record : records) {
            // and voila, column values in an array. Works with Lists as well
            int ranking = Integer.valueOf(record.get(0));
            String playerName = StringUtils.split(record.get(1),",")[0];
            String positionRanking;

            if(hasPositionRankings) {
                positionRanking = record.get(2);
            } else {
                positionRanking = String.valueOf(ranking);
            }

            EspnRanking espnRanking = new EspnRanking();
            espnRanking.setPlayerName(playerName);
            espnRanking.setPlayerRanking(ranking);
            espnRanking.setPositionRanking(positionRanking);
            espnRankings.add(espnRanking);
        }

        return espnRankings;
    }
}
