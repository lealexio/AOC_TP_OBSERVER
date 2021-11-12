package middle;

import java.util.concurrent.Future;

public interface Channel {
    Future update();

    Future getValue();
}
