package workshop;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PetSanctuaryController {

    final PetSanctuary petSanctuary;

    public PetSanctuaryController(PetSanctuary petSanctuary) {
        this.petSanctuary = petSanctuary;
    }

    static class PageInfo {
        String startCursor;
        String endCursor;
        boolean hasNextPage;
        boolean hasPreviousPage;

        public String getStartCursor() {
            return startCursor;
        }

        public void setStartCursor(String startCursor) {
            this.startCursor = startCursor;
        }

        public String getEndCursor() {
            return endCursor;
        }

        public void setEndCursor(String endCursor) {
            this.endCursor = endCursor;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }
    }

    static class PetConnection {
        List<PetEdge> edges;
        PageInfo pageInfo;

        public List<PetEdge> getEdges() {
            return edges;
        }

        public void setEdges(List<PetEdge> edges) {
            this.edges = edges;
        }

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }
    }

    static class PetEdge {
        String cursor;
        PetSanctuary.Pet pet;
    }

    @QueryMapping
    public PetConnection pets(@Argument Integer first, @Argument String after,
                              @Argument Integer last, @Argument String before) {
//        if (first != null) {
//            petSanctuary.getPetsAfter(first, after);
//        } else if (last != null) {
//            petSanctuary.getPetsBefore(last, before);
//        } else {
//            petSanctuary.get
//        }
        return null;
    }
}
