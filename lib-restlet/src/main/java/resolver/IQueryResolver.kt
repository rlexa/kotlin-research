package resolver

interface IQueryResolver {
  fun resolve(pathParams: List<Pair<String, String>>, queryParams: List<Pair<String, String>>): Any
}