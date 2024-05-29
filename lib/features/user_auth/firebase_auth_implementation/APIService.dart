import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:Amfibi_App/features/user_auth/presentation/pages/model/user.dart';

class ApiService {
  final String baseUrl;

  ApiService({required this.baseUrl});

  // Generic method to handle HTTP GET requests
  Future<Map<String, dynamic>> _getRequest(String endpoint) async {
    final response = await http
        .get(Uri.parse('$baseUrl/$endpoint'))
        .timeout(Duration(seconds: 10));
    return _processResponse(response);
  }

  // Generic method to handle HTTP POST requests
  Future<Map<String, dynamic>> _postRequest(
      String endpoint, Map<String, dynamic> body) async {
    final response = await http
        .post(
          Uri.parse('$baseUrl/$endpoint'),
          headers: {'Content-Type': 'application/json'},
          body: jsonEncode(body),
        )
        .timeout(Duration(seconds: 10));
    return _processResponse(response);
  }

  // Method to process the response and handle errors
  Map<String, dynamic> _processResponse(http.Response response) {
    if (response.statusCode == 200) {
      return jsonDecode(response.body);
    } else {
      throw Exception(
          'Failed with status code: ${response.statusCode}, body: ${response.body}');
    }
  }

  Future<Map<String, dynamic>> getUserProfile(String userId) async {
    return await _getRequest('users/$userId');
  }

  Future<Map<String, dynamic>> login(String email, String password) async {
    return await _postRequest('login', {'email': email, 'password': password});
  }

  Future<Map<String, dynamic>> register(
      String email, String password, String username) async {
    return await _postRequest('register',
        {'email': email, 'password': password, 'username': username});
  }

  Future<Map<String, dynamic>> resetPassword(String email) async {
    return await _postRequest('reset-password', {'email': email});
  }
}

class UserService extends ApiService {
  UserService() : super(baseUrl: 'https://www.kiraryuuki.com/api/users');

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
      throw Exception('Failed to load users: $e');
    }
  }
}
