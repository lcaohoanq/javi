- Java is tough, sometimes

# Naming Convention

- Packages:
    - Always lower case
    - Package names should be unique
    - Use your internet domain name, reversed, as a prefix for the package name
        - Domain name components that are Java keywords should have that component start with an underscore
- Classes:
    - PascalCase
    - Class names should be nouns (they represend things)
    - Should start with a capital letter
    - Each word in the name should also start with a capital (LinkedList...)
- Interfaces
    - PascalCase
    - Should start with a capital letter
    - Consider what object implementing the interface will become of what they will be able to do
- Methods
    - camelCase
    - often verb
    - reflect the function performed or the result returned
- Constants
    - ALL UPPER_CASE
    - "final" keyword
- Variables:

# Java Bean

A JavaBean is just a standard. It is a regular Java class, except it follows certain conventions:

- All properties are private
- Public getters/setters
- A public no-argument constructor (public all-argument constructor, optional)
- Implements Serializable.

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable  {
  private int age;
  private String name;
}
```

## So, why we need to follow:

- The 'beans' of JavaBeans are classes that encapsulate one or more objects into a single standardized object (the bean).
  This standardization allows the beans to be handled in a more generic fashion, allowing easier code reuse and introspection

# POJO

- What is POJO?
- Can't Extend anything
- Can't Implement anything
- No outside annotaion

```java
public class Cat {

  int age;
  String name;

}
```

- Not a POJO!

```java
public class Cat implements Animals {

  int age;
  String name;

  @Override
  public void run(){
    System.out.println(String.format("%s can be run", this.name));
  }

}
```

# DTO (Data Transfer Object)

![image](https://github.com/user-attachments/assets/daa8e1b5-e08c-4ca5-b67c-2679c0c79391)

- I prefer using record (java 16+) for visualize the DTO

```java
public record SubcategoryDTO(
    @NotNull(message = "Category id is required") @JsonProperty("category_id") Long categoryId,
    @NotEmpty(message = "Sub Category name is required") Set<String> name
) {}
```

```java
@JsonPropertyOrder({
    "id",
    "name",
    "created_at",
    "updated_at"
})
public record CategoryResponse(
    Long id,
    String name,
    TreeSet<Subcategory> subcategories,
    @JsonIgnore @JsonProperty("created_at") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime createdAt,
    @JsonIgnore @JsonProperty("updated_at") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime updatedAt
) {}
```

```java
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Id
    @SequenceGenerator(name = "categories_seq", sequenceName = "categories_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq")
    @Column(name="id", unique=true, nullable=false)
    @JsonProperty("id")
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "category")
    private Set<Subcategory> subcategories;

}
```

```java
    @PostMapping("/sub")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<ApiResponse<CreateNewSubcategoryResponse>> createSubCategory(
        @Valid @RequestBody SubcategoryDTO subcategoryDTO,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.<CreateNewSubcategoryResponse>builder()
                .message("Create sub category successfully")
                .statusCode(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(categoryService.createSubCategory(subcategoryDTO))
                .build()
        );
    }
```

```java
    @Override
    public CreateNewSubcategoryResponse createSubCategory(SubcategoryDTO subcategoryDTO)
        throws DataNotFoundException, DataAlreadyExistException {

        Category category = categoryRepository.findById(subcategoryDTO.categoryId())
            .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        if (subcategoryRepository.existsByCategoryIdAndNameIn(category.getId(), subcategoryDTO.name())) {
            throw new DataAlreadyExistException("Subcategory already exist in category: " + category.getId());
        }

        subcategoryDTO.name().forEach(name -> {
            subcategoryRepository.save(
                Subcategory.builder()
                    .name(name)
                    .category(category)
                    .build());
        });

        return new CreateNewSubcategoryResponse(categoryMapper.toCategoryResponse(category));
    }
```
