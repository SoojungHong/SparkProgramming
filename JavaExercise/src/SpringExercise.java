/**
 * Created by a613274 on 10.07.2017.
 */
public class SpringExercise {
/*
    public String name;

    //constructor
    @Autowired
    public SpringExercise(@Value("${property.name}") String name) {
        this.name = name;
    }

    //ToDo : How to use BiFunction
    private BiFunction<Integer, Integer, ResultPage<ArticlePublication>> fetchPage(
            final LocalDateTime dateFrom,
            final LocalDateTime dateTo) {

        return (size, from) -> {
            Search search = new Search.Builder(
                    articlesQueryBuilder.build(dateFrom, dateTo, size, from))
                                    .addIndex(PUBLICATION_INDEX)
                                    .addType(PUBLICATION_TYPE)
                                    .addIndex(publicationIndex)
                                    .addType(publicationType)
                    .build();

            SearchResult searchResult = searchInElasticSearch(search);
            List<ArticlePublication> articlePublications = searchResult
                    .getHits(ArticlePublication.class).stream()
                    .map(h -> h.source)
                    .collect(toList());

            return new ResultPage<>(articlePublications, searchResult.getTotal());
        };
    }
}
*/
}
