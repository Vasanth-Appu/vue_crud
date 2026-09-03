import js from '@eslint/js'
import pluginVue from 'eslint-plugin-vue'
import globals from 'globals'

export default [
  {
    ignores: [
      'node_modules/',
      'dist/',
      'coverage/'
    ]
  },

  js.configs.recommended,

  ...pluginVue.configs['flat/recommended'],

  {
    files: ['**/*.{js,vue}'],

    languageOptions: {
      ecmaVersion: 'latest',
      sourceType: 'module',
      globals: {
        ...globals.browser,
        ...globals.node
      }
    },

    rules: {
      // Add your custom rules here
      'vue/multi-word-component-names': 'off'
    }
  }
]