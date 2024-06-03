import 'apiService.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/model/user.dart';

class UserService extends ApiService {
  UserService() : super(baseUrl: 'https://www.kiraryuuki.com/api');

  // Fetches a list of users from the API
  Future<List<User>> fetchUsers() async {
    try {
      final result = await _getRequest('users?page=2');
      List<User> users = List<User>.from(
        result['data'].map(
          (user) => User.fromJson(user),
        ),
      );
      return users;
    } catch (e) {
      print('Error in fetchUsers: $e');
      throw Exception('Failed to load users: $e');
    }
  }
}
