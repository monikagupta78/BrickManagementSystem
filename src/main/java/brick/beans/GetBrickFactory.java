package brick.beans;

import brick.model.Brick;
import brick.model.ChemicallySetBrick;
import brick.model.FiredBrick;
import brick.model.UnFiredBrick;
import brick.utils.BrickType;
import org.springframework.stereotype.Component;

import static brick.utils.BrickType.CHEMICALLY_SET;
import static brick.utils.BrickType.FIRED;
import static brick.utils.BrickType.UN_FIRED;

@Component
public class GetBrickFactory {

    public Brick getBrick(String brickType){

        if(brickType == null){
            return null;
        }
        BrickType brickTypeValue = BrickType.valueOf(brickType);

        if(brickTypeValue.equals(UN_FIRED)) {
            return new UnFiredBrick();
        }
        else if(brickTypeValue.equals(FIRED)){
            return new FiredBrick();
        }
        else if(brickTypeValue.equals(CHEMICALLY_SET)) {
            return new ChemicallySetBrick();
        }
        return null;
    }
}