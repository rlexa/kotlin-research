package resolver

class QueryResolverStatic(
  private val data: Any
) : IQueryResolver {
  override fun resolve(pathParams: List<Pair<String, String>>, queryParams: List<Pair<String, String>>): Any {
    return data
  }
}