package sample.city;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class VoteController {

    private enum CandidateShortCut{
        e("emma"),
        r("racheal"),
        s("shane"),
        j("james");
        private String candidateName;
        CandidateShortCut(String name){
            candidateName=name;
        }
        public String getCandidateName(){
            return candidateName;
        }
    }
private Map<String,Integer> voteMap=new ConcurrentHashMap<>();
    {
        voteMap.put("emma",0);
        voteMap.put("racheal",0);
        voteMap.put("shane",0);
        voteMap.put("james",0);
    }
    @RequestMapping("/{candidate}")
    public ResponseEntity voteCandidate(@PathVariable("candidate") String candidate) {
        String candidateName=CandidateShortCut.valueOf(candidate).getCandidateName();
        Integer count = voteMap.get(candidateName);
        if(count==null){
            count=0;
        }
        voteMap.put(candidateName,count+1);
        return ResponseEntity.ok(voteMap);
    }

    @RequestMapping("/result")
    public ResponseEntity getVoteResults() {
        return ResponseEntity.ok(voteMap);
    }

}
